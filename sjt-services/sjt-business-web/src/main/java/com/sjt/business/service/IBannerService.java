package com.sjt.business.service;

import com.sjt.business.api.dto.res.HomePageBannersDTO;

/**
 * @author: yilan.hu
 * @data: 2019/7/15
 */
public interface IBannerService {

    /**
     * 获取首页banner图
     * @return
     */
    HomePageBannersDTO getHomePageBanners();
}
