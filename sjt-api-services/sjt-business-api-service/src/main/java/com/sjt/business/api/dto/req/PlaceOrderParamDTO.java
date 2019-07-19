package com.sjt.business.api.dto.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author: yilan.hu
 * @data: 2019/7/19
 */
@ApiModel("下单 Request params")
@Data
public class PlaceOrderParamDTO {

    /**
     * 收货地址id
     */
    @ApiModelProperty("收货地址id")
    private Long receivingId;

    /**
     * 订单详情
     */
    @ApiModelProperty("订单详情")
    private List<OrderItemParamDTO> orderItems;
}
