package com.sjt.business.api.impl;

import com.sjt.business.api.dto.res.HomePageBannersDTO;
import com.sjt.business.api.dto.res.HomePageNotesDTO;
import com.sjt.business.api.dto.res.NotesDTO;
import com.sjt.business.api.expose.HomePageApi;
import com.sjt.business.service.IBannerService;
import com.sjt.business.service.INotesService;
import com.sjt.common.base.result.ResultDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author: yilan.hu
 * @data: 2019/7/15
 */
@RestController
public class HomePageApiService implements HomePageApi {

    @Autowired
    private IBannerService iBannerService;

    @Autowired
    private INotesService iNotesService;

    @Override
    public ResultDTO<HomePageBannersDTO> getHomePageBanners() {
        HomePageBannersDTO homePageBanners = iBannerService.getHomePageBanners();
        return ResultDTO.data(homePageBanners);
    }

    @Override
    public ResultDTO<HomePageNotesDTO> getNotes() {
        HomePageNotesDTO homePageNotesDTO = iNotesService.getNotes();
        return ResultDTO.data(homePageNotesDTO);
    }
}
