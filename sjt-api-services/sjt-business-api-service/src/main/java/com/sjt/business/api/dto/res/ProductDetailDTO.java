package com.sjt.business.api.dto.res;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author: yilan.hu
 * @data: 2019/7/11
 */
@ApiModel("商品信息")
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
     * 商品价格(单位：元)
     */
    @ApiModelProperty("商品价格(单位：元)")
    private BigDecimal price;

    /**
     * 优惠金额(单位：元)
     */
    @ApiModelProperty("优惠金额(单位：元)")
    private BigDecimal discountAmount;

    /**
     * 商品发布状态(1-上架, 2-下架)
     */
    @ApiModelProperty("商品发布状态(1-上架, 2-下架)")
    private String publishStatus;

    /**
     * 商品描述
     */
    @ApiModelProperty("商品描述")
    private String descript;

    /**
     * 商品主图
     */
    @ApiModelProperty("商品主图")
    private String mainImage;

    /**
     * 商品详情
     */
    @ApiModelProperty("商品详情")
    private String productDetails;

    /**
     * 商品属性
     */
    @ApiModelProperty(value = "商品属性", dataType = "List")
    private List<ProductPropertiesDTO> properties;

    /**
     * 商品图片
     */
    @ApiModelProperty(value = "商品图片", dataType = "List")
    private List<ProductPicDTO> productPics;
}
