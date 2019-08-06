package com.sjt.business.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author: yilan.hu
 * @data: 2019/8/6
 */
@Data
@TableName("t_product_detail_desc")
public class ProductDetailDesc extends Model<ProductDetailDesc> implements Serializable {

    private static final long serialVersionUID = -4940275895051886773L;

    /**
     * 自增id
     */
    @TableId
    private String id;

    /**
     * 商品id
     */
    private String productId;

    /**
     * 描述内容类型(1-图片, 2-文本)
     */
    private String type;

    /**
     * 内容
     */
    private String content;

    /**
     * 排序编号
     */
    private String sortNum;

    @Override
    protected Serializable pkVal() {
        return id;
    }
}
