package com.sjt.wechat.config;

import com.lly835.bestpay.config.WxPayH5Config;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: yilan.hu
 * @data: 2019/8/1
 */
public class PayConfig {

    @Autowired
    private WxBaseInfo wxBaseInfo;

    @Bean
    public BestPayServiceImpl bestPayService(WxBaseInfo wxBaseInfo) {
        BestPayServiceImpl bestPayService = new BestPayServiceImpl();

        WxPayH5Config wxPayH5Config = new WxPayH5Config();
        wxPayH5Config.setAppId(wxBaseInfo.getAppletId());
        wxPayH5Config.setMchId(wxBaseInfo.getMchId());
        wxPayH5Config.setMchKey(wxBaseInfo.getMchSecret());
        wxPayH5Config.setNotifyUrl(wxBaseInfo.getNotifyUrl());

        bestPayService.setWxPayH5Config(wxPayH5Config);

        return bestPayService;
    }
}
