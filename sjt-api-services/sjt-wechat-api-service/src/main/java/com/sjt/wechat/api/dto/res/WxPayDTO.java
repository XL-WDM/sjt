package com.sjt.wechat.api.dto.res;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author: yilan.hu
 * @data: 2019/8/1
 */
@ApiModel("微信支付")
@Data
public class WxPayDTO {

    /**
     * 时间戳
     */
    @ApiModelProperty("时间戳")
    private String timeStamp;


    /**
     * 随机字符串
     */
    @ApiModelProperty("随机字符串")
    private String nonceStr;


    /**
     * 统一下单接口返回的 prepay_id 参数值，提交格式如：prepay_id=***
     */
    @ApiModelProperty("统一下单接口返回的 prepay_id 参数值，提交格式如：prepay_id=***")
    private String packAge;


    /**
     * 签名算法
     */
    @ApiModelProperty("签名算法")
    private String signType;


    /**
     * 签名
     */
    @ApiModelProperty("签名")
    private String paySign;
}
