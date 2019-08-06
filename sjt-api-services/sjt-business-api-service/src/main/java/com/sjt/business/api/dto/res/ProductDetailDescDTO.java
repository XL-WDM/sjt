package com.sjt.business.api.dto.res;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author: yilan.hu
 * @data: 2019/8/6
 */
@ApiModel("商品详情描述内容")
@Data
public class ProductDetailDescDTO {

    /**
     * 内容类型(1-图片, 2-文本)
     */
    @ApiModelProperty("内容类型(1-图片, 2-文本)")
    private String type;

    /**
     * 内容
     */
    @ApiModelProperty("内容")
    private String content;
}
