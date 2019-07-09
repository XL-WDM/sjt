package com.stj.business.api.expose;

import com.stj.business.api.dto.req.SignParamDTO;
import com.stj.business.api.dto.res.SignUserDTO;
import com.stj.common.base.result.R;
import com.stj.common.base.result.ResultModel;
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
     * @return
     */
    @ApiOperation(value = "登录", response = SignUserDTO.class)
    @PostMapping("/sign")
    ResultModel<SignUserDTO> sign(SignParamDTO signParamDTO, HttpServletResponse response);
}
