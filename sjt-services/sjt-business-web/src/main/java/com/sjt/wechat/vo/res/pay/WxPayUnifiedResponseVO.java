package com.sjt.wechat.vo.res.pay;

import lombok.Data;
import org.simpleframework.xml.Element;

/**
 * @author: yilan.hu
 * @data: 2019/8/1
 */
@Data
public class WxPayUnifiedResponseVO extends WxPayResultVO {

    /**
     * 小程序ID
     */
    @Element(
            name = "appid",
            required = false
    )
    private String appid;

    /**
     * 商户号
     */
    @Element(
            name = "mch_id",
            required = false
    )
    private String mchId;

    /**
     * 设备号
     */
    @Element(
            name = "device_info",
            required = false
    )
    private String deviceInfo;

    /**
     * 随机字符串
     */
    @Element(
            name = "nonce_str",
            required = false
    )
    private String nonceStr;

    /**
     * 签名
     */
    @Element(
            name = "sign",
            required = false
    )
    private String sign;

    /**
     * 交易类型
     */
    @Element(
            name = "trade_type",
            required = false
    )
    private String tradeType;

    /**
     * 预支付交易会话标识
     */
    @Element(
        name = "prepay_id",
        required = false
    )
    private String prepayId;

    /**
     * 二维码链接
     */
    @Element(
        name = "code_url",
        required = false
    )
    private String codeUrl;
}
