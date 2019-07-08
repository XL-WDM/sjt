package com.stj.business.api.impl;

import com.stj.business.api.OauthApi;
import com.stj.business.api.dto.req.SignParamDTO;
import com.stj.business.api.dto.res.SignUserDTO;
import com.stj.business.service.IUserService;
import com.stj.common.base.result.ResultModel;
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
    public ResultModel<SignUserDTO> sign(SignParamDTO signParamDTO, HttpServletResponse response) {
        SignUserDTO sign = iUserService.sign(signParamDTO, response);
        return ResultModel.data(sign);
    }
}
