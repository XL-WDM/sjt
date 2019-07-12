package com.sjt.business.api.dto.res;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author: yilan.hu
 * @data: 2019/7/11
 */
@ApiModel("商品属性")
@Data
public class ProductPropertiesDTO {

    /**
     * 属性名称
     */
    @ApiModelProperty("属性名称")
    private String propertiesName;

    /**
     * 属性值
     */
    @ApiModelProperty("属性值")
    private String propertiesValue;
}
