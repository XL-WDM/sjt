package com.sjt.business.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 商品多规格信息
 * @author: yilan.hu
 * @data: 2019/7/30
 */
@Data
@TableName("t_product_spec")
public class ProductSpec extends Model<ProductSpec> implements Serializable {
    private static final long serialVersionUID = 734608830042740070L;

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
     * 规格名称
     */
    private String specName;

    /**
     * 规格单价(单位: 分)
     */
    private BigDecimal price;

    /**
     * 规格图片
     */
    private String specImage;

    /**
     * 规格库存
     */
    private Integer stockNum;

    /**
     * 规格库存下单未结算数(<= 库存数量, 下单+n, 订单结束-n)
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
