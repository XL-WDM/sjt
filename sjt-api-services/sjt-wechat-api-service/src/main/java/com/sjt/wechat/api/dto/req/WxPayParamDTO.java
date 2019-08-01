package com.sjt.wechat.api.dto.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author: yilan.hu
 * @data: 2019/8/1
 */
@ApiModel("微信支付 request params")
@Data
public class WxPayParamDTO {

    /**
     * 订单id
     */
    @ApiModelProperty("订单id")
    private Long orderId;

    /**
     * 配送地址id
     */
    @ApiModelProperty("配送地址id")
    private Long addressId;
}
