package com.sjt.business.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author: yilan.hu
 * @data: 2019/7/11
 */
@Data
@TableName("t_product_properties")
public class ProductProperties extends Model<ProductProperties> implements Serializable {
    private static final long serialVersionUID = 7562951040787136015L;

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
     * 属性名称
     */
    private String propertiesName;

    /**
     * 属性值
     */
    private String propertiesValue;

    /**
     * 排序编号
     */
    private Integer sortNum;

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
