package com.sjt.business.api.dto.res;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author: yilan.hu
 * @data: 2019/7/19
 */
@ApiModel
@Data
public class OrderItemDTO {

    /**
     * 商品名称
     */
    @ApiModelProperty("商品名称")
    private String productName;

    /**
     * 商品图片地址
     */
    @ApiModelProperty("商品图片地址")
    private String productImg;

    /**
     * 商品描述
     */
    @ApiModelProperty("商品描述")
    private String productDescript;

    /**
     * 商品单价(下单时)
     */
    @ApiModelProperty("商品单价(下单时)")
    private BigDecimal price;

    /**
     * 商品数量
     */
    @ApiModelProperty("商品数量")
    private Integer num;
}
