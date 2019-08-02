package com.sjt.wechat.service.impl;

import com.lly835.bestpay.enums.BestPayTypeEnum;
import com.lly835.bestpay.model.PayRequest;
import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;
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
import com.sjt.common.utils.CheckObjects;
import com.sjt.common.utils.JsonUtils;
import com.sjt.common.utils.LocalUtils;
import com.sjt.wechat.api.dto.req.WxPayParamDTO;
import com.sjt.wechat.api.dto.res.WxPayDTO;
import com.sjt.wechat.config.WxBaseInfo;
import com.sjt.wechat.service.IWxPayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private BestPayServiceImpl bestPayService;

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
        Address address = new Address();
        address.setId(wxPayParamDTO.getAddressId());
        Long userId = WebUserContext.getContext().getId();
        address.setUserId(userId);
        address = addressMapper.selectAddressByIdAndUserId(address);
        CheckObjects.isNull(address, "物流地址不存在");

        // 4.获取用户openid
        UserOauths userOauths = new UserOauths();
        userOauths.setUserId(userId);
        userOauths.setOauthType(DataBaseConstant.OauthType.WX_APPLET.getCode());
        userOauths = userOauthsMapper.selectOneByUserIdAndType(userOauths);
        CheckObjects.isNull(userOauths, "用户 openid 为空");

        // 5.设置物流信息
        order.setAddressId(address.getId());
        order.updateById();

        PayRequest payRequest = new PayRequest();
        payRequest.setOpenid(userOauths.getOauthId());
        payRequest.setOrderAmount(1D);
        payRequest.setOrderId(order.getOrderNo());
        payRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_H5);
        payRequest.setSpbillCreateIp(LocalUtils.getIp());
        payRequest.setOrderName("sjt-pay");

        log.info("【微信支付】 request -> {}", JsonUtils.toJson(payRequest));
        PayResponse pay = null;
        try {
            pay = bestPayService.pay(payRequest);
            CheckObjects.isNull(pay, "微信支付发起失败, result is null");
        } catch (Exception e) {
            log.error("【微信支付】 发起失败 -> {}", e);
        }
        log.info("【微信支付】 response -> {}", JsonUtils.toJson(pay));

        WxPayDTO wxPayDTO = new WxPayDTO();
        wxPayDTO.setNonceStr(pay.getNonceStr());
        wxPayDTO.setPaySign(pay.getPaySign());
        wxPayDTO.setSignType(BaseConstant.Character.MD5);
        wxPayDTO.setTimeStamp(String.valueOf(System.currentTimeMillis()));
        wxPayDTO.setPrepayId(pay.getPackAge());

        /*// 6.支付信息封装
        String body = "sjt-pay";
        // 随机字符串
        String nonceStr = MD5Utils.getMD5(UUID.randomUUID().toString());
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
        String url = WechatConstant.UNIFIED_ORDER;
        log.info("【微信支付】 request -> {}", JsonUtils.toJson(vo));

        ResponseEntity<String> entity = restTemplate.postForEntity(url, XmlUtils.toString(vo), String.class);
        if (HttpStatus.OK != entity.getStatusCode()) {
            log.error("# 【微信支付发起失败】, 网络异常 -> status: {}", entity.getStatusCodeValue());
            throw new GlobalException("微信支付发起失败, 网络异常");
        }
        WxPayUnifiedResponseVO wxPayUnifiedResponseVO = XmlUtils.toObject(entity.getBody(), WxPayUnifiedResponseVO.class);
        log.info("【微信支付】 response -> {}", JsonUtils.toJson(wxPayUnifiedResponseVO));
        if (wxPayUnifiedResponseVO.isFail()) {
            throw new GlobalException("微信支付发起失败, msg: " + wxPayUnifiedResponseVO.getReturnMsg());
        }
        if (!wxPayUnifiedResponseVO.isSuccess()) {
            throw new GlobalException("微信支付发起失败, msg: " + wxPayUnifiedResponseVO.getErrCode());
        }

        // 8.处理结果
        WxPayDTO wxPayDTO = new WxPayDTO();
        wxPayDTO.setNonceStr(nonceStr);
        wxPayDTO.setPaySign(sign);
        wxPayDTO.setSignType(BaseConstant.Character.MD5);
        wxPayDTO.setTimeStamp(String.valueOf(System.currentTimeMillis()));
        wxPayDTO.setPrepayId("prepay_id" + BaseConstant.Unicode.EQUAL + wxPayUnifiedResponseVO.getPrepayId());

        return wxPayDTO;*/

        return wxPayDTO;
    }
}
