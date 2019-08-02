package com.sjt.wechat.service.impl;

import com.sjt.business.constant.DataBaseConstant;
import com.sjt.business.entity.Address;
import com.sjt.business.entity.Order;
import com.sjt.business.entity.UserOauths;
import com.sjt.business.mapper.AddressMapper;
import com.sjt.business.mapper.OrderMapper;
import com.sjt.business.mapper.UserOauthsMapper;
import com.sjt.business.web.config.WebUserContext;
import com.sjt.common.base.constant.BaseConstant;
import com.sjt.common.base.constant.ResultConstant;
import com.sjt.common.exceptions.GlobalException;
import com.sjt.common.utils.*;
import com.sjt.wechat.api.dto.req.WxPayParamDTO;
import com.sjt.wechat.api.dto.res.WxPayDTO;
import com.sjt.wechat.config.WxBaseInfo;
import com.sjt.wechat.constant.WechatConstant;
import com.sjt.wechat.service.IWxPayService;
import com.sjt.wechat.utils.PaySignatureUtils;
import com.sjt.wechat.vo.req.pay.WxPayUnifiedRequestVO;
import com.sjt.wechat.vo.res.pay.WxPayNotifyResponseVO;
import com.sjt.wechat.vo.res.pay.WxPayUnifiedResponseVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author: yilan.hu
 * @data: 2019/7/31
 */
@Slf4j
@Service
public class WxPayServiceImpl implements IWxPayService {

    @Autowired
    private WxBaseInfo wxBaseInfo;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private AddressMapper addressMapper;

    @Autowired
    private UserOauthsMapper userOauthsMapper;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public WxPayDTO wxH5UnifiedOrder(WxPayParamDTO wxPayParamDTO) {
        // 1.参数校验
        CheckObjects.isNull(wxPayParamDTO, ResultConstant.PARAMETERS_CANNOT_BE_NULL);
        CheckObjects.isNull(wxPayParamDTO.getOrderId(), "请选择订单");
        CheckObjects.isNull(wxPayParamDTO.getAddressId(), "请选择物流地址");

        // 2.查询订单
        Order order = orderMapper.selectById(wxPayParamDTO.getOrderId());
        CheckObjects.isNull(order, "订单不存在");

        // 3.查询物流地址
        Long userId = WebUserContext.getContext().getId();
        Address address = addressMapper.selectAddressByIdAndUserId(wxPayParamDTO.getAddressId(), userId);
        CheckObjects.isNull(address, "物流地址不存在");

        // 4.获取用户openid
        UserOauths userOauths = userOauthsMapper.selectOneByUserIdAndType(String.valueOf(userId),
                DataBaseConstant.OauthType.WX_APPLET.getCode());
        CheckObjects.isNull(userOauths, "用户 openid 为空");

        // 5.更新物流信息
        order.setAddressId(address.getId());
        order.updateById();

        // 6.支付信息封装
        String body = "sjt-pay";
        // 随机字符串
        String nonceStr = RandomUtils.getRandomString();
        WxPayUnifiedRequestVO vo = new WxPayUnifiedRequestVO();
        vo.setAppid(wxBaseInfo.getAppletId());
        vo.setMchId(wxBaseInfo.getMchId());
        vo.setSignType(BaseConstant.Character.MD5);
        vo.setBody(body);
        vo.setOutTradeNo(order.getOrderNo());
        vo.setNotifyUrl(wxBaseInfo.getNotifyUrl());
        vo.setFeeType(BaseConstant.FeeType.CNY);
        vo.setTotalFee(order.getPayment().intValue());
        vo.setSpbillCreateIp(LocalUtils.getIp());
        vo.setTradeType("JSAPI");
        vo.setProductId(order.getOrderNo());
        vo.setOpenid(userOauths.getOauthId());
        vo.setNonceStr(nonceStr);
        String sign = PaySignatureUtils.wxSign(MapUtils.buildMap(vo), wxBaseInfo.getMchSecret());
        vo.setSign(sign);

        // 7.发起支付
        String requestXml = XmlUtils.toString(vo);
        log.info("【微信支付】 request -> {}", requestXml);
        ResponseEntity<String> entity = restTemplate.postForEntity(WechatConstant.UNIFIED_ORDER,
                requestXml,
                String.class);
        CheckObjects.predicate(entity.getStatusCode(), status -> HttpStatus.OK != status,
                "微信支付发起失败, 网络异常",
                () -> {log.error("## 【微信支付发起失败】, 网络异常 -> status: {}", entity.getStatusCodeValue());});
        // 7-1.获取body
        String responseXml = entity.getBody();

        log.info("【微信支付】 response -> {}", responseXml);

        // 7-2.xml -> 对象
        WxPayUnifiedResponseVO wxPayUnifiedResponseVO = XmlUtils.toObject(responseXml, WxPayUnifiedResponseVO.class);
        CheckObjects.isNull(wxPayUnifiedResponseVO, "微信支付发起失败", () -> {log.error("## 【微信支付】 报文解析异常");});
        CheckObjects.predicate(wxPayUnifiedResponseVO.isFail(), b -> b,
                "微信支付发起失败, return_msg: " + wxPayUnifiedResponseVO.getReturnMsg(),
                () -> {log.error("## 【微信支付发起失败】 return_msg -> {}", wxPayUnifiedResponseVO.getReturnMsg());});
        CheckObjects.predicate(wxPayUnifiedResponseVO.isSuccess(), b -> !b,
                "微信支付发起失败, err_code: " + wxPayUnifiedResponseVO.getErrCode(),
                () -> {log.error("## 【微信支付发起失败】 err_code -> {}", wxPayUnifiedResponseVO.getReturnMsg());});

        // 8.处理结果
        WxPayDTO wxPayDTO = new WxPayDTO();
        wxPayDTO.setNonceStr(nonceStr);
        wxPayDTO.setPaySign(sign);
        wxPayDTO.setSignType(BaseConstant.Character.MD5);
        wxPayDTO.setTimeStamp(String.valueOf(System.currentTimeMillis()));
        wxPayDTO.setPrepayId("prepay_id" + BaseConstant.Character.EQUAL + wxPayUnifiedResponseVO.getPrepayId());

        return wxPayDTO;
    }

    @Override
    public void payNotify(String notifyData) {
        log.info("【微信支付结果通知】 request -> {}", notifyData);

        // 1.参数校验
        CheckObjects.isNull(notifyData, ResultConstant.PARAMETERS_CANNOT_BE_NULL);

        // 2.签名验证
        CheckObjects.predicate(PaySignatureUtils.vxVerify(XmlUtils.toMap(notifyData), wxBaseInfo.getMchSecret()),
                b -> !b, "签名验证失败");

        // 3.xml转对象
        WxPayNotifyResponseVO wxPayNotifyResponseVO = XmlUtils.toObject(notifyData, WxPayNotifyResponseVO.class, false);
        CheckObjects.isNull(wxPayNotifyResponseVO, "报文解析失败",
                () -> {log.error("【微信支付结果通知】 报文解析失败");});
        CheckObjects.predicate(wxPayNotifyResponseVO.isFail(), status -> status,
                "微信支付结果通知返回数据失败, 处理终止",
                () -> {log.error("【微信支付结果通知】 失败");});

        // 3.处理结果

    }
}
