package com.sjt.business.api.dto.res;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author: yilan.hu
 * @data: 2019/7/19
 */
@ApiModel("订单信息")
@Data
public class OrderDTO {

    /**
     * 订单id
     */
    @ApiModelProperty("订单id")
    private Long id;

    /**
     * 订单编号
     */
    @ApiModelProperty("订单编号")
    private String orderNo;

    /**
     * 商品总价
     */
    @ApiModelProperty("商品总价")
    private BigDecimal totalAmount;

    /**
     * 订单总价
     */
    @ApiModelProperty("订单总价")
    private BigDecimal orgAmount;

    /**
     * 运费(快递)
     */
    @ApiModelProperty("运费(快递)")
    private BigDecimal postFee;

    /**
     * 订单状态(1-待支付, 2-待发货, 3-待收货, 4-已完成, 5-已取消)
     */
    @ApiModelProperty("订单状态(1-待支付, 2-待发货, 3-待收货, 4-已完成, 5-已取消)")
    private String status;

    /**
     * 订单详情
     */
    @ApiModelProperty("订单id")
    private List<OrderItemDTO> orderItems;
}
