package com.sjt.business.api.dto.res;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author: yilan.hu
 * @data: 2019/7/30
 */
@Data
public class ShoppingCartDTO {

    /**
     * 商品id
     */
    @ApiModelProperty("商品id")
    private Long productId;

    /**
     * 商品规格id
     */
    @ApiModelProperty("商品规格id")
    private Long productSpecId;

    /**
     * 商品图片
     */
    @ApiModelProperty("商品图片")
    private String imgUrl;

    /**
     * 商品名称
     */
    @ApiModelProperty("商品名称")
    private String productName;

    /**
     * 商品规格名称
     */
    @ApiModelProperty("商品规格名称")
    private String productSpecName;

    /**
     * 商品描述
     */
    @ApiModelProperty("商品描述")
    private String descript;

    /**
     * 商品价格
     */
    @ApiModelProperty("商品价格")
    private BigDecimal price;
}
