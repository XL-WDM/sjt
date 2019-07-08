package com.stj.business.service.impl;

import com.stj.business.api.dto.req.SignParamDTO;
import com.stj.business.api.dto.res.SignUserDTO;
import com.stj.business.service.IUserService;
import com.stj.common.base.constant.ResultConstant;
import com.stj.common.utils.CheckObjects;

/**
 * @author: yilan.hu
 * @data: 2019/7/8
 */
public class UserServiceImpl implements IUserService {

    @Override
    public SignUserDTO sign(SignParamDTO signParamDTO) {
        // 参数校验
        CheckObjects.isNull(signParamDTO, ResultConstant.PARAMETERS_CANNOT_BE_NULL);



        return null;
    }
}
