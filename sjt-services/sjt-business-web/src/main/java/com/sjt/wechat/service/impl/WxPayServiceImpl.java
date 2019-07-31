package com.sjt.wechat.service.impl;

import com.sjt.common.base.constant.BaseConstant;
import com.sjt.common.exceptions.GlobalException;
import com.sjt.common.utils.LocalUtils;
import com.sjt.common.utils.MD5Utils;
import com.sjt.common.utils.XstreamUtils;
import com.sjt.wechat.config.WxBaseInfo;
import com.sjt.wechat.constant.WechatConstant;
import com.sjt.wechat.service.IWxPayService;
import com.sjt.wechat.vo.req.pay.WxAppletUnifiedOrderVO;
import io.swagger.annotations.Authorization;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
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
    private RestTemplate restTemplate;

    @Override
    public String appletUnifiedOrder() {

        // 获取参数
        String appid = wxBaseInfo.getAppletId();
        String body = "山尖田-抗霾舒畅柠果膏";
        String deviceInfo = "WEB";
        String mchId = wxBaseInfo.getAppletMchId();
        String nonceStr = MD5Utils.getMD5(UUID.randomUUID().toString());
        nonceStr = "ibuaiVcKdpRxkhJA";

        // 参数封装
        WxAppletUnifiedOrderVO vo = new WxAppletUnifiedOrderVO();
        vo.setAppid(appid);
        vo.setMch_id(mchId);
        vo.setDevice_info(deviceInfo);
        vo.setSign_type(BaseConstant.Character.MD5);
        vo.setBody(body);
        vo.setDetail("hahaha");
        vo.setOut_trade_no("6555574723953176576");
        vo.setFee_type(BaseConstant.FeeType.CNY);
        vo.setTotal_fee(1);
        vo.setSpbill_create_ip(LocalUtils.getIp());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(BaseConstant.FormatDate.SIMPLE_DATE_TIME);
        vo.setTime_expire(LocalDateTime.now().format(formatter));
        vo.setTime_expire(LocalDateTime.now().plusDays(1).format(formatter));
        vo.setNotify_url("http://www.baidu.com");
        vo.setTrade_type("JSAPI");
        vo.setProduct_id("6555574723953176576");
        vo.setOpenid("otXxp5NykBfEBg18lWcdvw_RbEJc");
        vo.setNonce_str(nonceStr);

        Map<String, String> map = new HashMap<>();
        map.put("appid", appid);
        map.put("mch_id", mchId);
        map.put("nonce_str", nonceStr);
        map.put("body", body);
        map.put("device_info", deviceInfo);
        vo.setSign(createSign(map, wxBaseInfo.getAppletAppsecret()));

        // 生成xml
        String xml = XstreamUtils.toXml(vo, "xml");

        System.out.println(xml);

        String url = WechatConstant.UNIFIED_ORDER;

        ResponseEntity<String> entity = restTemplate.postForEntity(url, xml, String.class);
        if (HttpStatus.OK != entity.getStatusCode()) {
            log.error("# 微信小程序统一下单失败 -> status: {}", entity.getStatusCodeValue());
            throw new GlobalException("微信小程序统一下单失败");
        }

        String resultXml = entity.getBody();

        System.out.println(resultXml);

        return resultXml;
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
            builder.append(entry.getValue());
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
