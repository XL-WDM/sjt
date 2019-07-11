package com.stj.business.api.dto.res;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author: yilan.hu
 * @data: 2019/7/11
 */
@ApiModel("商品图片")
@Data
public class ProductPicDTO {

    /**
     * 图片URL
     */
    @ApiModelProperty("图片URL")
    private String picUrl;

    /**
     * 图片描述
     */
    @ApiModelProperty("图片描述")
    private String descript;
}
