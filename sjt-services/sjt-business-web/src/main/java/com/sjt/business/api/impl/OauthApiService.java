package com.sjt.business.api.impl;

import com.sjt.business.api.expose.OauthApi;
import com.sjt.business.api.dto.req.SignParamDTO;
import com.sjt.business.api.dto.res.SignUserDTO;
import com.sjt.business.service.IUserService;
import com.sjt.common.base.result.ResultDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * @author: yilan.hu
 * @data: 2019/7/1
 */
@RestController
public class OauthApiService implements OauthApi {

    @Autowired
    private IUserService iUserService;

    @Override
    public ResultDTO<SignUserDTO> sign(SignParamDTO signParamDTO, HttpServletResponse response) {
        SignUserDTO sign = iUserService.sign(signParamDTO, response);
        return ResultDTO.data(sign);
    }
}
