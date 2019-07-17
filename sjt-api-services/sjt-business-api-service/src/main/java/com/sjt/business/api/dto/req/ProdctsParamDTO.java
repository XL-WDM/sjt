package com.sjt.business.api.dto.req;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @author: yilan.hu
 * @data: 2019/7/17
 */
@ApiModel("商品列表")
@Data
public class ProdctsParamDTO extends PageParamDTO {

    /**
     * 商品分类Id
     */
    private Long categoryId;
}
