package com.sjt.business.api.expose;

import com.sjt.business.api.dto.res.HomePageBannersDTO;
import com.sjt.business.api.dto.res.HomePageNotesDTO;
import com.sjt.common.base.result.ResultDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author: yilan.hu
 * @data: 2019/7/15
 */
@Api(description = "首页")
@RequestMapping("/home-page")
public interface HomePageApi {

    /**
     * 获取首页banner
     * @return
     */
    @ApiOperation(value = "获取首页banner", response = HomePageBannersDTO.class)
    @GetMapping("/open-api/banners")
    ResultDTO<HomePageBannersDTO> getHomePageBanners();

    /**
     * 获取山田日记列表信息
     * @return
     */
    @ApiOperation(value = "获取山田日记列表信息", response = HomePageNotesDTO.class)
    @GetMapping("/open-api/notes")
    ResultDTO<HomePageNotesDTO> getNotes();
}
