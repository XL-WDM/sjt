package com.sjt.wechat.vo.req.pay;

import lombok.Data;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * @author: yilan.hu
 * @data: 2019/5/24
 */
@Root(
    name = "xml",
    strict = false
)
@Data
public class WxPayUnifiedRequestVO {
    
    /**
     * 小程序ID
     */
    @Element(
        name = "appid"
    )
    private String appid;

    /**
     * 商户号
     */
    @Element(
        name = "mch_id"
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
        name = "nonce_str"
    )
    private String nonceStr;

    /**
     * 签名
     */
    @Element(
        name = "sign"
    )
    private String sign;

    /**
     * 签名类型
     */
    @Element(
        name = "sign_type",
        required = false
    )
    private String signType;

    /**
     * 商品描述
     */
    @Element(
        name = "body"
    )
    private String body;

    /**
     * 商品详情
     */
    @Element(
        name = "detail",
        required = false
    )
    private String detail;

    /**
     * 附加数据
     */
    @Element(
        name = "attach",
        required = false
    )
    private String attach;

    /**
     * 商户订单号
     */
    @Element(
        name = "out_trade_no"
    )
    private String outTradeNo;

    /**
     * 标价币种
     */
    @Element(
        name = "fee_type",
        required = false
    )
    private String feeType;

    /**
     * 标价金额(单位为分)
     */
    @Element(
        name = "total_fee"
    )
    private Long totalFee;

    /**
     * 终端IP
     */
    @Element(
        name = "spbill_create_ip"
    )
    private String spbillCreateIp;

    /**
     * 交易起始时间
     */
    @Element(
        name = "time_start",
        required = false
    )
    private String timeStart;

    /**
     * 交易结束时间
     */
    @Element(
        name = "time_expire",
        required = false
    )
    private String timeExpire;

    /**
     * 订单优惠标记
     */
    @Element(
        name = "goods_tag",
        required = false
    )
    private String goodsTag;

    /**
     * 通知地址
     */
    @Element(
        name = "notify_url"
    )
    private String notifyUrl;

    /**
     * 交易类型
     */
    @Element(
        name = "trade_type"
    )
    private String tradeType;

    /**
     * 商品ID
     */
    @Element(
        name = "product_id",
        required = false
    )
    private String productId;

    /**
     * 指定支付方式
     */
    @Element(
        name = "limit_pay",
        required = false
    )
    private String limitPay;

    /**
     * 用户标识
     */
    @Element(
        name = "openid"
    )
    private String openid;

    /**
     * 电子发票入口开放标识
     */
    @Element(
        name = "receipt",
        required = false
    )
    private String receipt;

    /**
     * 场景信息
     */
    @Element(
        name = "scene_info",
        required = false
    )
    private String sceneInfo;
}
