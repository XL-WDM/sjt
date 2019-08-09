package com.sjt.business.api.impl;

import com.sjt.business.api.dto.req.SignParamDTO;
import com.sjt.business.api.dto.res.SignUserDTO;
import com.sjt.business.api.expose.OauthApi;
import com.sjt.business.constant.EncryptionSlotConstant;
import com.sjt.business.constant.WxCookieConstant;
import com.sjt.business.service.IOauthService;
import com.sjt.common.base.result.ResultDTO;
import com.sjt.common.utils.AesEncryptUtils;
import com.sjt.common.utils.ResponseUtils;
import com.sjt.common.utils.SpringUtils;
import com.sjt.wechat.api.dto.res.WxAccessTokenDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;

/**
 * @author: yilan.hu
 * @data: 2019/7/1
 */
@RestController
public class OauthApiService implements OauthApi {

    @Autowired
    private IOauthService iOauthService;

    @Override
    public ResultDTO<SignUserDTO> sign(@RequestBody SignParamDTO signParamDTO) {
        SignUserDTO sign = iOauthService.sign(signParamDTO, SpringUtils.getResponse());
        return ResultDTO.data(sign);
    }

    @Override
    public ResultDTO getOauthAccessToken(String code, HttpServletResponse response) {
        WxAccessTokenDTO oauthAccessToken = iOauthService.getOauthAccessToken(code);

        String token = AesEncryptUtils.encrypt(oauthAccessToken.getRefreshToken(),
                EncryptionSlotConstant.WX_REFRESH_TOKEN_SLOT);

        // 设置 cookie
        ResponseUtils.setCookie(response, WxCookieConstant.WX_OAUTH_ACCESS_TOKEN, token);
        return ResultDTO.success();
    }

    @GetMapping("/business/sign")
    public ModelAndView businessLoginPage() {
        return new ModelAndView("login/index");
    }
}
