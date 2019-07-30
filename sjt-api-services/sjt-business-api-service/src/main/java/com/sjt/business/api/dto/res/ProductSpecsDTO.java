package com.sjt.business.api.dto.res;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author: yilan.hu
 * @data: 2019/7/30
 */
@ApiModel("商品规格信息")
@Data
public class ProductSpecsDTO {

    /**
     * 规格组名称
     */
    @ApiModelProperty("规格组名称")
    private String specGropName;

    /**
     * 商品规格
     */
    @ApiModelProperty("商品规格")
    List<ProductSpecDTO> specs;
}
