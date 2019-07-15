package com.sjt.business.api.expose;

import com.sjt.business.api.dto.req.SignParamDTO;
import com.sjt.business.api.dto.res.SignUserDTO;
import com.sjt.common.base.result.ResultDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;

/**
 * @author: yilan.hu
 * @data: 2019/7/1
 */
@Api(description = "授权")
@RequestMapping("/auth")
public interface OauthApi {

    /**
     * 登录
     * @param signParamDTO
     * @param response
     * @return
     */
    @ApiOperation(value = "登录", response = SignUserDTO.class)
    @PostMapping("/sign")
    ResultDTO<SignUserDTO> sign(SignParamDTO signParamDTO, HttpServletResponse response);
}
