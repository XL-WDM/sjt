package com.sjt.business.api.impl;

import com.sjt.business.api.dto.res.HomePageBannersDTO;
import com.sjt.business.api.expose.HomePageApi;
import com.sjt.business.service.IBannerService;
import com.sjt.common.base.result.ResultDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: yilan.hu
 * @data: 2019/7/15
 */
@RestController
public class HomePageApiService implements HomePageApi {

    @Autowired
    private IBannerService iBannerService;

    @Override
    public ResultDTO<HomePageBannersDTO> getHomePageBanners() {
        HomePageBannersDTO homePageBanners = iBannerService.getHomePageBanners();
        return ResultDTO.data(homePageBanners);
    }
}
