package com.sjt.business.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 商品分类
 * @author: yilan.hu
 * @data: 2019/7/12
 */
@Data
@TableName("t_product_category")
public class ProductCategory extends Model<ProductCategory> implements Serializable {
    private static final long serialVersionUID = -7256930434590322725L;

    /**
     * 自增id
     */
    @TableId
    private Long id;

    /**
     * 分类名称
     */
    private String categoryName;

    /**
     * 分类层级
     */
    private String categoryLevel;

    /**
     * 状态(0-删除, 1-可用)
     */
    private String status;

    /**
     * 分类图标
     */
    private String icon;

    /**
     * 分类图片URL
     */
    private String imgUrl;

    /**
     * 链接
     */
    private String url;

    /**
     * 父级编号
     */
    private Long pid;

    /**
     * 创建时间
     */
    private LocalDateTime createDate;

    /**
     * 更新时间
     */
    private LocalDateTime updateDate;

    @Override
    protected Serializable pkVal() {
        return id;
    }
}
