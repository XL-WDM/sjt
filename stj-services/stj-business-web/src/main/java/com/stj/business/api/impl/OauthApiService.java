package com.stj.business.api.impl;

import com.stj.business.api.OauthApi;
import com.stj.business.api.dto.req.SignParamDTO;
import com.stj.business.api.dto.res.SignUserDTO;
import com.stj.common.base.result.ResultModel;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: yilan.hu
 * @data: 2019/7/1
 */
@RestController
public class OauthApiService implements OauthApi {

    @Override
    public ResultModel<SignUserDTO> sign(SignParamDTO signParamDTO) {

        return null;
    }
}
