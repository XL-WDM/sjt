package com.sjt.wechat.service;

import com.sjt.wechat.api.dto.res.WxAccessTokenDTO;
import com.sjt.wechat.api.dto.res.WxAppletSessionKeyDTO;
import com.sjt.wechat.api.dto.res.WxSnsapiUserInfoDTO;

/**
 * @author: yilan.hu
 * @data: 2019/7/9
 */
public interface IWxOauthService {

    /**
     * 通过code换取网页授权access_token
     * @param code
     * @return
     */
    WxAccessTokenDTO getOauthAccessToken(String code);

    /**
     * 刷新access_token
     * @param refreshToken
     * @return
     */
    WxAccessTokenDTO refreshoauthAccessToken(String refreshToken);

    /**
     * 获取用户基本
     * @param accessToken
     * @param openid
     * @return
     */
    WxSnsapiUserInfoDTO getWxSnsapiUserInfo(String accessToken, String openid);

    /**
     * 微信小程序登录(auth.code2Session)
     * @param code
     * @return
     */
    WxAppletSessionKeyDTO getWxAppletSeesionKey(String code);
}
