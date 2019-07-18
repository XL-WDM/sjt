package com.sjt.business.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author: yilan.hu
 * @data: 2019/7/18
 */
@Data
@TableName("t_product_stock")
public class ProductStock extends Model<ProductStock> implements Serializable {
    private static final long serialVersionUID = -8008941217736249542L;

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
     * 商品库存数量
     */
    private Integer productStockNum;

    /**
     * 下单库存
     */
    private Integer orderStockNum;

    /**
     * 版本号
     */
    private Integer version;

    @Override
    protected Serializable pkVal() {
        return id;
    }
}
