package com.sjt.wechat.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author: yilan.hu
 * @data: 2019/7/9
 */
@Data
@Component
@ConfigurationProperties("sjt.wechat")
public class WxBaseInfo {
    /**
     * 微信公众号第三方用户唯一凭证
     */
    private String appId;

    /**
     * 微信公众号第三方用户唯一凭证密钥，即appsecret
     */
    private String appsecret;

    /**
     * 微信开放平台第三方用户唯一凭证
     */
    private String openAppId;

    /**
     * 微信开放平台第三方用户唯一凭证密钥，即appsecret
     */
    private String openAppsecret;

    /**
     * 微信小程序第三方用户唯一凭证A
     */
    private String appletId;

    /**
     * 微信小程序第三方用户唯一凭证密钥，即appsecret
     */
    private String appletAppsecret;

    /**
     * 微信小程序关联的商户号
     */
    private String appletMchId;
}
