package com.sjt.business.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author: yilan.hu
 * @data: 2019/7/18
 */
@Data
@TableName("t_order")
public class Order extends Model<Order> implements Serializable {
    private static final long serialVersionUID = -2919274231151209686L;

    /**
     * 自增id
     */
    @TableId
    private Long id;

    /**
     * 订单号
     */
    private String orderNo;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 实付金额(单位：分)
     */
    private Long payment;

    /**
     * 支付类型(1-线上支付, 2-线下支付)
     */
    private String paymentType;

    /**
     * 邮费(单位：分)
     */
    private Long postFee;

    /**
     * 订单状态(1-未付款, 2-已付款, 3-未发货, 4-已发货, 5-交易成功, 6-交易关闭)
     */
    private String status;

    /**
     * 订单创建时间
     */
    private LocalDateTime createDate;

    /**
     * 订单更新时间
     */
    private LocalDateTime updateDate;

    /**
     * 付款时间
     */
    private LocalDateTime paymentDate;

    /**
     * 发货时间
     */
    private LocalDateTime consignDate;

    /**
     * 交易完成时间
     */
    private LocalDateTime endDate;

    /**
     * 交易关闭时间
     */
    private LocalDateTime closeDate;

    /**
     * 物流名称
     */
    private String shippingName;

    /**
     * 物流单号
     */
    private String shippingCode;

    /**
     * 买家留言
     */
    private String buyerMessage;

    /**
     * 买家是否评论(0-否, 1-是)
     */
    private String buyerRate;

    @Override
    protected Serializable pkVal() {
        return id;
    }
}