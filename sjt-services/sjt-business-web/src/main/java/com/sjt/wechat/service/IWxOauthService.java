package com.sjt.wechat.service;

import com.sjt.wechat.vo.res.WxAccessTokenVO;
import com.sjt.wechat.vo.res.WxAppletSessionKeyVO;

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
    WxAccessTokenVO getOauthAccessToken(String code);

    /**
     * 刷新access_token
     * @param refreshToken
     * @return
     */
    WxAccessTokenVO refreshoauthAccessToken(String refreshToken);

    /**
     * 登录(auth.code2Session)
     * @param code
     * @return
     */
    WxAppletSessionKeyVO getWxAppletSeesionKey(String code);
}
