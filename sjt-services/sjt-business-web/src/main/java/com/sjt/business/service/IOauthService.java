package com.sjt.business.service;

import com.sjt.business.api.dto.req.SignParamDTO;
import com.sjt.business.api.dto.res.SignUserDTO;
import com.sjt.wechat.api.dto.res.WxAccessTokenDTO;

import javax.servlet.http.HttpServletResponse;

/**
 * @author: yilan.hu
 * @data: 2019/7/8
 */
public interface IOauthService {

    /**
     * 登录
     * @param signParamDTO
     * @param response
     * @return
     */
    SignUserDTO sign(SignParamDTO signParamDTO, HttpServletResponse response);

    /**
     * 获取微信网页授权凭证
     * @param code
     * @return
     */
    WxAccessTokenDTO getOauthAccessToken(String code);
}
