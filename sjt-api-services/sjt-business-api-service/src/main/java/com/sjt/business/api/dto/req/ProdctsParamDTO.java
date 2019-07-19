package com.sjt.business.api.dto.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author: yilan.hu
 * @data: 2019/7/17
 */
@ApiModel("商品列表 Request params")
@Data
public class ProdctsParamDTO extends PageParamDTO {

    /**
     * 商品分类Id
     */
    @ApiModelProperty("商品分类Id")
    private Long categoryId;
}
