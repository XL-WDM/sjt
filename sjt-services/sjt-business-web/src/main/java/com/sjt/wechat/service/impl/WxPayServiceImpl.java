package com.sjt.wechat.service.impl;

import com.alibaba.fastjson.JSONObject;
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
import com.sjt.wechat.vo.req.pay.WxPayUnifiedRequestVO;
import com.sjt.wechat.vo.res.pay.WxPayUnifiedResponseVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.time.format.DateTimeFormatter;
import java.util.*;

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
    public WxPayDTO appletUnifiedOrder(WxPayParamDTO wxPayParamDTO) {
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

        // 6.支付信息封装
        String body = "山尖田-支付";
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
        vo.setTotalFee(order.getPayment().longValue());
        vo.setSpbillCreateIp(LocalUtils.getIp());
        vo.setTradeType("JSAPI");
        vo.setProductId(order.getOrderNo());
        vo.setOpenid(userOauths.getOauthId());
        vo.setNonceStr(nonceStr);
        HashMap map = JSONObject.parseObject(JSONObject.toJSONString(vo), HashMap.class);
        String sign = createSign(map, wxBaseInfo.getAppletAppsecret());
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

        return wxPayDTO;
    }

    /**
     * 生成签名
     * @param appid
     * @param body
     * @param device_info
     * @param mch_id
     * @param nonce_str
     * @return
     */
    private String createSign(Map<String, String> map, String key) {
        if (map == null || StringUtils.isEmpty(key)) {
            return null;
        }

        StringBuilder builder = new StringBuilder();

        map = new TreeMap<>(map);

        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (builder.length() != 0) {
                builder.append(BaseConstant.Character.AND);
            }
            builder.append(entry.getKey());
            builder.append("=");
            builder.append(String.valueOf(entry.getValue()));
        }

        String keyValue = builder.toString();

        String signTemp = keyValue + "&key=" + key;

        return MD5Utils.getMD5(signTemp);
    }

    public static void main(String[] args){
        String s = "appid=wxd930ea5d5a258f4f&body=test&device_info=1000&mch_id=10000100&nonce_str=ibuaiVcKdpRxkhJA&key=192006250b4c09247ec02edce69f6a2d";
        String md5 = MD5Utils.getMD5(s);
        System.out.println(md5);
    }
}
