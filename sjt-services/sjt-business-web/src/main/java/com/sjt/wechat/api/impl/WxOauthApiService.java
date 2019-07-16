package com.sjt.wechat.api.impl;

import com.sjt.common.base.result.ResultDTO;
import com.sjt.wechat.api.dto.res.WxAccessTokenDTO;
import com.sjt.wechat.api.expose.WxOauthApi;
import com.sjt.wechat.service.IWxOauthService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author: yilan.hu
 * @data: 2019/7/9
 */
public class WxOauthApiService implements WxOauthApi {

    @Autowired
    private IWxOauthService iWxOauthService;

    @Override
    public ResultDTO<WxAccessTokenDTO> getOauthAccessToken(String code) {
        WxAccessTokenDTO wxAccessTokenDTO = iWxOauthService.getOauthAccessToken(code);
        return ResultDTO.success();
    }
}
