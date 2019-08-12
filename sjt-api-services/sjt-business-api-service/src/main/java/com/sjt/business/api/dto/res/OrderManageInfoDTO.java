package com.sjt.business.api.dto.res;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author: yilan.hu
 * @data: 2019/8/12
 */
@ApiModel("订单管理信息")
@Data
public class OrderManageInfoDTO {
    /**
     * 订单id
     */
    @ApiModelProperty("订单id")
    private Long id;

    /**
     * 订单号
     */
    @ApiModelProperty("订单号")
    private String orderNo;

    /**
     * 用户信息
     */
    @ApiModelProperty("用户信息")
    private SignUserDTO user;

    /**
     * 收货地址
     */
    @ApiModelProperty("收货地址")
    private AddressDTO address;

    /**
     * 订单总金额(单位：分)
     */
    @ApiModelProperty("订单总金额(单位：分)")
    private BigDecimal orgAmount;

    /**
     * 支付金额(单位：分)
     */
    @ApiModelProperty("支付金额(单位：分)")
    private BigDecimal payment;

    /**
     * 订单状态(1-待支付, 2-待发货, 3-待收货, 4-已完成, 5-已取消)
     */
    @ApiModelProperty("订单状态(1-待支付, 2-待发货, 3-待收货, 4-已完成, 5-已取消)")
    private String status;

    /**
     * 订单创建时间
     */
    @ApiModelProperty("订单创建时间")
    private LocalDateTime createDate;

    /**
     * 付款时间
     */
    @ApiModelProperty("付款时间")
    private LocalDateTime paymentDate;

    /**
     * 发货时间
     */
    @ApiModelProperty("发货时间")
    private LocalDateTime consignDate;

    /**
     * 物流名称
     */
    @ApiModelProperty("物流名称")
    private String shippingName;

    /**
     * 物流单号
     */
    @ApiModelProperty("物流单号")
    private String shippingCode;

    /**
     * 买家留言
     */
    @ApiModelProperty("买家留言")
    private String buyerMessage;

    /**
     * 买家是否评论(0-否, 1-是)
     */
    @ApiModelProperty("买家是否评论(0-否, 1-是)")
    private String buyerRate;
}
