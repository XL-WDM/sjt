package com.sjt.wechat.vo.res.pay;

import lombok.Data;
import org.simpleframework.xml.Element;

/**
 * 微信支付结果通知
 * @author: yilan.hu
 * @data: 2019/8/2
 */
@Data
public class WxPayNotifyResponseVO extends WxPayResultVO {

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
     * 商户号
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
     * 签名类型
     */
    @Element(
        name = "sign_type",
        required = false
    )
    private String signType;

    /**
     * 用户标识
     */
    @Element(
        name = "openid",
        required = false
    )
    private String openid;

    /**
     * 是否关注公众账号
     */
    @Element(
        name = "is_subscribe",
        required = false
    )
    private String isSubscribe;

    /**
     * 交易类型
     */
    @Element(
        name = "trade_type",
        required = false
    )
    private String tradeType;

    /**
     * 付款银行
     */
    @Element(
        name = "bank_type",
        required = false
    )
    private String bankType;

    /**
     * 订单金额
     */
    @Element(
        name = "total_fee",
        required = false
    )
    private Integer totalFee;

    /**
     * 应结订单金额
     */
    @Element(
        name = "settlement_total_fee",
        required = false
    )
    private Integer settlementTotalFee;

    /**
     * 货币种类
     */
    @Element(
        name = "fee_type",
        required = false
    )
    private String feeType;

    /**
     * 现金支付金额
     */
    @Element(
        name = "cash_fee",
        required = false
    )
    private Integer cashFee;

    /**
     * 现金支付货币类型
     */
    @Element(
        name = "cash_fee_type",
        required = false
    )
    private String cashFeeType;

    /**
     * 总代金券金额
     */
    @Element(
        name = "coupon_fee",
        required = false
    )
    private Integer couponFee;

    /**
     * 代金券使用数量
     */
    @Element(
        name = "coupon_count",
        required = false
    )
    private Integer couponCount;

    /**
     * 代金券类型
     */
    @Element(
        name = "coupon_type",
        required = false
    )
    private String couponType;

    /**
     * 代金券ID
     */
    @Element(
        name = "coupon_id",
        required = false
    )
    private String couponId;

    /**
     * 微信支付订单号
     */
    @Element(
        name = "transaction_id",
        required = false
    )
    private String transactionId;

    /**
     * 商户订单号
     */
    @Element(
        name = "out_trade_no",
        required = false
    )
    private String outTradeNo;

    /**
     * 商家数据包
     */
    @Element(
        name = "attach",
        required = false
    )
    private String attach;

    /**
     * 商家数据包
     */
    @Element(
        name = "time_end",
        required = false
    )
    private String timeEnd;
}
