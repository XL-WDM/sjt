package com.sjt.wechat.api.expose;

import com.sjt.common.base.result.ResultDTO;
import com.sjt.wechat.api.dto.res.WxAccessTokenDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author: yilan.hu
 * @data: 2019/7/9
 */
@Api(value = "微信网页授权", tags = "微信网页授权")
@RequestMapping("/wx/oauth")
public interface WxOauthApi {

    /**
     * 通过code换取网页授权access_token
     * @param code 换取access_token的票据
     * @param response
     * @return
     */
    @ApiOperation(value = "通过code换取网页授权access_token", response = WxAccessTokenDTO.class)
    @GetMapping("/open-api/access-token")
    ResultDTO<WxAccessTokenDTO> getOauthAccessToken(String code);
}
