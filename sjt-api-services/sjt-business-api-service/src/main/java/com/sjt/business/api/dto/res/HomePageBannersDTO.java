package com.sjt.business.api.dto.res;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author: yilan.hu
 * @data: 2019/7/15
 */
@ApiModel("首页banner图")
@Data
public class HomePageBannersDTO {

    /**
     * 首页顶部轮播图
     */
    @ApiModelProperty("首页顶部轮播图")
    private List<BannerDTO> topBanner;

    /**
     * GIF小视频
     */
    @ApiModelProperty("GIF小视频")
    private BannerDTO gifBanner;

    /**
     * 山田日记banner
     */
    @ApiModelProperty("山田日记banner")
    private BannerDTO stNotesBanner;

    /**
     * 首页中部轮播图
     */
    @ApiModelProperty(value = "首页中部轮播图", dataType = "List")
    private List<BannerDTO> centerBanner;
}
