package com.sjt.business.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author: yilan.hu
 * @data: 2019/7/18
 */
@Data
@TableName("t_order_item")
public class OrderItem extends Model<OrderItem> implements Serializable {
    private static final long serialVersionUID = -7364774920227960419L;

    /**
     * 自增id
     */
    @TableId
    private Long id;

    /**
     * 商品id
     */
    private Long productId;

    /**
     * 订单id
     */
    private Long orderId;

    /**
     * 商品购买数量
     */
    private Integer num;

    /**
     * 商品单价(单位：分)
     */
    private BigDecimal price;

    /**
     * 商品总金额
     */
    private BigDecimal totalFee;

    @Override
    protected Serializable pkVal() {
        return id;
    }
}
