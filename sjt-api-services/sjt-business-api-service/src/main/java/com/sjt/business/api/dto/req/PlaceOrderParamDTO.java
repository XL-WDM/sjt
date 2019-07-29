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
     * 订单详情
     */
    @ApiModelProperty(value = "订单详情", dataType = "List")
    private List<OrderItemParamDTO> orderItems;
}
