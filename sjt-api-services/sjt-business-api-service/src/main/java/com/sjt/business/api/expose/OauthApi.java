package com.sjt.business.api.expose;

import com.sjt.business.api.dto.req.SignParamDTO;
import com.sjt.business.api.dto.res.SignUserDTO;
import com.sjt.common.base.result.ResultDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;

/**
 * @author: yilan.hu
 * @data: 2019/7/1
 */
@Api(description = "授权", tags = "授权")
@RequestMapping("/oauth")
public interface OauthApi {

    /**
     * 登录
     * @param signParamDTO
     * @param response
     * @return
     */
    @ApiOperation(value = "登录", response = SignUserDTO.class)
    @PostMapping("/sign")
    ResultDTO<SignUserDTO> sign(@RequestBody SignParamDTO signParamDTO);

    /**
     * 通过code换取网页授权access_token
     * @param code 换取access_token的票据
     * @param response
     * @return
     */
    @ApiImplicitParam(name = "code", value = "网页授权", required = true)
    @ApiOperation(value = "通过code换取网页授权access_token")
    @GetMapping("/open-api/wx/access-token")
    ResultDTO getOauthAccessToken(String code, HttpServletResponse response);
}
