package com.sjt.business.api.dto.res;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author: yilan.hu
 * @data: 2019/7/30
 */
@ApiModel("商品规格")
@Data
public class ProductSpecDTO {

    /**
     * 商品规格编号
     */
    @ApiModelProperty("商品规格编号")
    private Long id;

    /**
     * 商品规格名称
     */
    @ApiModelProperty("商品规格名称")
    private String specName;

    /**
     * 商品规格单价
     */
    @ApiModelProperty("商品规格单价")
    private BigDecimal price;

    /**
     * 商品图片
     */
    @ApiModelProperty("商品图片")
    private String specImage;

    /**
     * 商品库存
     */
    @ApiModelProperty("商品库存")
    private Integer stockNum;
}
