package com.sjt.business.api.dto.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author: yilan.hu
 * @data: 2019/7/29
 */
@ApiModel("商品搜索 Request params")
@Data
public class QueryProductParamDTO extends PageParamDTO {

    /**
     * 商品关键字
     */
    @ApiModelProperty("商品关键字")
    private String productKeyWord;
}
