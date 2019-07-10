package com.stj.wechat.api.impl;

import com.stj.common.base.result.ResultModel;
import com.stj.common.utils.ResponseUtils;
import com.stj.wechat.api.expose.WxOauthApi;
import com.stj.wechat.constant.WxCookieConstant;
import com.stj.wechat.service.IWxOauthService;
import com.stj.wechat.vo.res.WxAccessTokenVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * @author: yilan.hu
 * @data: 2019/7/9
 */
@RestController
public class WxOauthApiService implements WxOauthApi {

    @Autowired
    private IWxOauthService iWxOauthService;

    @Override
    public ResultModel getOauthAccessToken(String code, HttpServletResponse response) {
        WxAccessTokenVO oauthAccessToken = iWxOauthService.getOauthAccessToken(code);

        // 设置 cookie
        ResponseUtils.setCookie(response, WxCookieConstant.WX_OAUTH_ACCESS_TOKEN, oauthAccessToken.getAccess_token());

        return ResultModel.success();
    }
}
