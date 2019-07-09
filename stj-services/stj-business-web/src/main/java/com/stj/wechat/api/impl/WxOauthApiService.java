package com.stj.wechat.api.impl;

import com.stj.common.base.result.ResultModel;
import com.stj.wechat.api.expose.WxOauthApi;
import com.stj.wechat.service.IWxOauthService;
import com.stj.wechat.vo.res.WxAccessTokenVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: yilan.hu
 * @data: 2019/7/9
 */
@RestController
public class WxOauthApiService implements WxOauthApi {

    @Autowired
    private IWxOauthService iWxOauthService;

    @Override
    public ResultModel<String> getOauthAccessToken(String code) {
        WxAccessTokenVO oauthAccessToken = iWxOauthService.getOauthAccessToken(code);
        return ResultModel.data(oauthAccessToken.getAccess_token());
    }
}
