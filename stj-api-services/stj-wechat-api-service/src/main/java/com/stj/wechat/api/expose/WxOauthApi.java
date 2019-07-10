package com.stj.wechat.api.expose;

import com.stj.common.base.result.ResultModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;

/**
 * @author: yilan.hu
 * @data: 2019/7/9
 */
@Api(description = "微信网页授权")
@RequestMapping("/wx/oauth")
public interface WxOauthApi {

    /**
     * 通过code换取网页授权access_token
     * @param code 换取access_token的票据
     * @param response
     * @return
     */
    @ApiOperation(value = "通过code换取网页授权access_token")
    @GetMapping("/access-token")
    ResultModel getOauthAccessToken(String code, HttpServletResponse response);
}
