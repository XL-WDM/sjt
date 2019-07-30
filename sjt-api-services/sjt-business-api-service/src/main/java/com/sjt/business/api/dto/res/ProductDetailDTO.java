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
     * 商品id
     */
    @ApiModelProperty("商品id")
    private Long id;

    /**
     * 商品名称
     */
    @ApiModelProperty("商品名称")
    private String productName;

    /**
     * 商品最小价格(单位：元)
     */
    @ApiModelProperty("商品最小价格(单位：元)")
    private BigDecimal minPrice;

    /**
     * 商品最大价格(单位：元)
     */
    @ApiModelProperty("商品最大价格(单位：元)")
    private BigDecimal maxPrice;

    /**
     * 商品发布状态(1-上架, 2-下架)
     */
    @ApiModelProperty("商品发布状态(1-上架, 2-下架)")
    private String publishStatus;

    /**
     * 规格类型(1-单规格, 2-多规格)
     */
    @ApiModelProperty("规格类型(1-单规格, 2-多规格)")
    private String specType;

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
     * 商品规格
     */
    @ApiModelProperty("商品规格")
    private ProductSpecsDTO productSpec;

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

    /**
     * 销量
     */
    @ApiModelProperty("销量")
    private Integer soldOut;
}
