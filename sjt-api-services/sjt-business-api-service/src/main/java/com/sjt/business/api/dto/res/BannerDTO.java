package com.sjt.business.api.dto.res;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author: yilan.hu
 * @data: 2019/7/15
 */
@ApiModel
@Data
public class BannerDTO {

    /**
     * banner名称
     */
    @ApiModelProperty("banner名称")
    private String bannerName;

    /**
     * 图片url地址
     */
    @ApiModelProperty("图片url地址")
    private String imgUrl;

    /**
     * banner跳转地址
     */
    @ApiModelProperty("banner跳转地址")
    private String url;

    /**
     * 地址类型(1-内部地址, 2-外部地址)
     */
    @ApiModelProperty("地址类型(1-内部地址, 2-外部地址)")
    private String urlType;
}
