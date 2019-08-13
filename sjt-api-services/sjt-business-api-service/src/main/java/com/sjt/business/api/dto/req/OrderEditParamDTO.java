package com.sjt.business.api.dto.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author: yilan.hu
 * @data: 2019/8/13
 */
@ApiModel("订单管理-物流单录入 Request params")
@Data
public class OrderEditParamDTO {

    /**
     * 订单id
     */
    @ApiModelProperty("订单id")
    private Long orderId;

    /**
     * 物流单号
     */
    @ApiModelProperty("物流单号")
    private String shippingCode;

}
