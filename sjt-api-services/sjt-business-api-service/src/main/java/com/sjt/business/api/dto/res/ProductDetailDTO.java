package com.sjt.business.api.dto.res;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author: yilan.hu
 * @data: 2019/7/11
 */
@ApiModel("商品详情信息")
@Data
public class ProductDetailDTO {
    /**
     * 自增id
     */
    @ApiModelProperty("商品描述")
    private Long id;

    /**
     * 商品名称
     */
    @ApiModelProperty("商品名称")
    private String productName;

    /**
     * 商品价格(单位：分)
     */
    @ApiModelProperty("商品价格(单位：分)")
    private Long price;

    /**
     * 优惠金额(单位：分)
     */
    @ApiModelProperty("优惠金额(单位：分)")
    private Long discountAmount;

    /**
     * 商品描述
     */
    @ApiModelProperty("商品描述")
    private String descript;

    /**
     * 商品属性
     */
    @ApiModelProperty("商品属性")
    private List<ProductPropertiesDTO> properties;

    /**
     * 商品图片
     */
    @ApiModelProperty("商品图片")
    private List<ProductPicDTO> productPics;
}
