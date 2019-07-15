package com.sjt.business.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.sjt.business.api.dto.res.BannerDTO;
import com.sjt.business.api.dto.res.HomePageBannersDTO;
import com.sjt.business.constant.DataBaseConstant;
import com.sjt.business.entity.Banner;
import com.sjt.business.mapper.BannerMapper;
import com.sjt.business.service.IBannerService;
import com.sjt.common.utils.BeanCopierUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author: yilan.hu
 * @data: 2019/7/15
 */
@Service
public class BannerServiceImpl implements IBannerService {

    @Autowired
    private BannerMapper bannerMapper;

    @Override
    public HomePageBannersDTO getHomePageBanners() {
        HomePageBannersDTO homePageBannersDTO = new HomePageBannersDTO();

        // 1.获取所有banner图
        List<Banner> banners = bannerMapper.selectList(new EntityWrapper<Banner>()
                .eq("status", "1").orderBy("sort_num"));

        // 2.获取首页顶部轮播图
        List<BannerDTO> topBanners = banners.stream()
                .filter(b -> DataBaseConstant.BannerType.TOP_BANNER.getCode().equals(b.getBannerType()))
                .map(b -> BeanCopierUtils.copyBean(b, BannerDTO.class))
                .collect(Collectors.toList());
        homePageBannersDTO.setTopBanner(topBanners);

        // 3.获取GIF小视频
        List<BannerDTO> gifBanners = banners.stream()
                .filter(b -> DataBaseConstant.BannerType.GIF_BANNER.getCode().equals(b.getBannerType()))
                .map(b -> BeanCopierUtils.copyBean(b, BannerDTO.class))
                .collect(Collectors.toList());
        Optional<BannerDTO> gifBanner = Optional.empty();
        if (gifBanners != null && !gifBanners.isEmpty()) {
            gifBanner = Optional.of(gifBanners.get(0));
        }
        homePageBannersDTO.setGifBanner(gifBanner.orElse(new BannerDTO()));

        // 4.获取山田日记banner
        List<BannerDTO> stDiaryBanners = banners.stream()
                .filter(b -> DataBaseConstant.BannerType.ST_DIARY_BANNER.getCode().equals(b.getBannerType()))
                .map(b -> BeanCopierUtils.copyBean(b, BannerDTO.class))
                .collect(Collectors.toList());
        Optional<BannerDTO> stDiaryBanner = Optional.empty();
        if (stDiaryBanners != null && !stDiaryBanners.isEmpty()) {
            stDiaryBanner = Optional.of(stDiaryBanners.get(0));
        }
        homePageBannersDTO.setStDiaryBanner(stDiaryBanner.orElse(new BannerDTO()));

        // 5.获取首页中部轮播图
        List<BannerDTO> centerBanners = banners.stream()
                .filter(b -> DataBaseConstant.BannerType.CENTER_BANNER.getCode().equals(b.getBannerType()))
                .map(b -> BeanCopierUtils.copyBean(b, BannerDTO.class))
                .collect(Collectors.toList());
        homePageBannersDTO.setCenterBanner(centerBanners);

        return homePageBannersDTO;
    }
}
