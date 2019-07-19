package com.sjt.business.api.dto.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author: yilan.hu
 * @data: 2019/7/19
 */
@ApiModel("订单查询参数")
@Data
public class OrderParamDTO extends PageParamDTO {

    /**
     * 订单状态(1-待支付, 2-待发货, 3-待收货, 4-已完成, 5-已取消)
     */
    @ApiModelProperty("订单状态(1-待支付, 2-待发货, 3-待收货, 4-已完成, 5-已取消)")
    private String status;
}
