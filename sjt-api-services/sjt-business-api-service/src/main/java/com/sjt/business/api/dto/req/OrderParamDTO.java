package com.sjt.business.api.dto.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author: yilan.hu
 * @data: 2019/7/18
 */
@ApiModel("下单请求参数")
@Data
public class OrderParamDTO {

    /**
     * 商品id
     */
    @ApiModelProperty("商品id")
    private Long productId;

    /**
     * 商品购买数量
     */
    @ApiModelProperty("商品购买数量")
    private Integer num;
}