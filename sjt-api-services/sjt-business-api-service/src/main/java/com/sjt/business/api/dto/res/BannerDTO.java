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
     * banner类型(1-首页top轮播图, 2-GIF小视频, 3-山田日记banner, 4-首页center轮播图)
     */
    @ApiModelProperty("banner类型(1-首页top轮播图, 2-GIF小视频, 3-山田日记banner, 4-首页center轮播图)")
    private String bannerType;

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
