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
 * @data: 2019/7/11
 */
@Data
@TableName("t_product_info")
public class Product extends Model<Product> implements Serializable {
    private static final long serialVersionUID = 4255927258734260231L;

    /**
     * 自增id
     */
    @TableId
    private Long id;

    /**
     * 商品名称
     */
    private String productName;

    /**
     * 供应商id
     */
    private Long supplierId;

    /**
     * 商品一级分类id
     */
    private Long oneLevelCategory;

    /**
     * 商品二级分类id
     */
    private Long twoLevelCategory;

    /**
     * 商品三级分类id
     */
    private Long threeLevelCategory;

    /**
     * 商品价格(单位：分)
     */
    private BigDecimal price;

    /**
     * 优惠金额(单位：分)
     */
    private BigDecimal discountAmount;

    /**
     * 商品发布状态(0-无效, 1-上架, 2-下架)
     */
    private String publishStatus;

    /**
     * 是否为新品推荐(0-否, 1-是)
     */
    private String newArrivals;

    /**
     * 商品详情
     */
    private String productDetails;

    /**
     * 商品主图
     */
    private String mainImage;

    /**
     * 商品描述
     */
    private String descript;

    /**
     * 商品录入时间
     */
    private LocalDateTime createDate;

    /**
     * 商品更新时间
     */
    private LocalDateTime updateDate;

    @Override
    protected Serializable pkVal() {
        return id;
    }
}
