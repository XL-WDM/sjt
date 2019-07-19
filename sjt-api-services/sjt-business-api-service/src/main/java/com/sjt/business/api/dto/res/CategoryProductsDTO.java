package com.sjt.business.api.dto.res;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author: yilan.hu
 * @data: 2019/7/17
 */
@ApiModel("商品分类列表")
@Data
public class CategoryProductsDTO {

    /**
     * 商品分类id
     */
    @ApiModelProperty("商品分类id")
    private Long categoryId;

    /**
     * 商品分类图片
     */
    @ApiModelProperty("商品分类图片")
    private String categoryImg;

    /**
     * 分类下所有商品
     */
    @ApiModelProperty("商品分类图片")
    private List<ProductDetailDTO> products;
}
