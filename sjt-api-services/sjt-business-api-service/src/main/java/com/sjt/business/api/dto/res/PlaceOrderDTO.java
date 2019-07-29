package com.sjt.business.api.dto.res;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author: yilan.hu
 * @data: 2019/7/29
 */
@ApiModel("下单返回")
@Data
public class PlaceOrderDTO {

    /**
     * 订单编号
     */
    @ApiModelProperty("订单编号")
    private Long id;

    /**
     * 订单号
     */
    @ApiModelProperty("订单号")
    private String orderNo;
}
