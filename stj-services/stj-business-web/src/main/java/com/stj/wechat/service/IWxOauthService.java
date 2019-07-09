package com.stj.wechat.service;

import com.stj.wechat.vo.res.WxAccessTokenVO;

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
}
