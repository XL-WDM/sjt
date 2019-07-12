package com.sjt.business.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 商品图片表
 * @author: yilan.hu
 * @data: 2019/7/11
 */
@Data
@TableName("t_product_pic")
public class ProductPic extends Model<ProductPic> implements Serializable {
    private static final long serialVersionUID = -3230234206738461006L;

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
     * 图片URL
     */
    private String picUrl;

    /**
     * 图片描述
     */
    private String descript;

    /**
     * 图片排序编号
     */
    private Integer sortNum;

    /**
     * 是否主图(0-否, 1-是)
     */
    private String isMaster;

    /**
     * 状态(0-无效, 1-有效)
     */
    private String status;

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
