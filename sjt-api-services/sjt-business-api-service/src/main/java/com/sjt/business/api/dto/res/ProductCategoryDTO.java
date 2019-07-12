package com.sjt.business.api.dto.res;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author: yilan.hu
 * @data: 2019/7/12
 */
@ApiModel("商品分类")
@Data
public class ProductCategoryDTO {
    /**
     * 自增id
     */
    @ApiModelProperty("自增id")
    private Long id;

    /**
     * 分类名称
     */
    @ApiModelProperty("分类名称")
    private String categoryName;

    /**
     * 分类层级
     */
    @ApiModelProperty("分类层级")
    private String categoryLevel;

    /**
     * 分类图标
     */
    @ApiModelProperty("分类图标")
    private String icon;

    /**
     * 分类图片URL
     */
    @ApiModelProperty("分类图片URL")
    private String imgUrl;

    /**
     * 链接
     */
    @ApiModelProperty("链接")
    private String url;

    /**
     * 子分类
     */
    @ApiModelProperty("子分类")
    private List<ProductCategoryDTO> children;
}
