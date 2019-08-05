package com.sjt.business.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author: yilan.hu
 * @data: 2019/8/5
 */
@Data
@TableName("t_payment_flow")
public class PaymentFlow extends Model<PaymentFlow> implements Serializable {
    private static final long serialVersionUID = 6269365060103700517L;

    /**
     * 自增id
     */
    @TableId
    private Long id;

    /**
     * 支付流水号
     */
    private String payNo;

    /**
     * 订单编号
     */
    private String orderNo;

    /**
     * 支付订单号(例如：微信支付订单号)
     */
    private String payOrderNo;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 支付金额(单位：分)
     */
    private BigDecimal amount;

    /**
     * 使用的积分
     */
    private Integer integral;

    /**
     * 支付类型(1-微信, 2-支付宝, 3-银联)
     */
    private String payType;

    /**
     * 支付状态(0-取消, 1-未支付, 2-已支付, 3-支付异常)
     */
    private String status;

    /**
     * 备注
     */
    private String note;

    /**
     * 创建时间
     */
    private LocalDateTime createDate;

    /**
     * 更新时间
     */
    private LocalDateTime updateDate;

    /**
     * 支付完成时间
     */
    private LocalDateTime payCompleteDate;

    @Override
    protected Serializable pkVal() {
        return id;
    }
}
