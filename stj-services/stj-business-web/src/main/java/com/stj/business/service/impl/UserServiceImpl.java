package com.stj.business.service.impl;

import com.stj.business.api.dto.req.SignParamDTO;
import com.stj.business.api.dto.res.SignUserDTO;
import com.stj.business.constant.DataBaseConstant;
import com.stj.business.entity.User;
import com.stj.business.service.IUserService;
import com.stj.business.strategy.sign.mode.SignModeHandler;
import com.stj.common.base.constant.ResultConstant;
import com.stj.common.utils.BeanCopierUtils;
import com.stj.common.utils.CheckObjects;
import org.springframework.stereotype.Service;

/**
 * @author: yilan.hu
 * @data: 2019/7/8
 */
@Service
public class UserServiceImpl implements IUserService {

    @Override
    public SignUserDTO sign(SignParamDTO signParamDTO) {
        // 1.参数校验
        CheckObjects.isNull(signParamDTO, ResultConstant.PARAMETERS_CANNOT_BE_NULL);
        String signMode = signParamDTO.getSignMode();
        CheckObjects.isEmpty(signMode, "登录方式不能为空");
        DataBaseConstant.SignMode signModeEnum = DataBaseConstant.SignMode.find(signMode);
        CheckObjects.isNull(signModeEnum, "登录方式格式不正确");

        // 2.获取处理器
        SignModeHandler handler = signModeEnum.getHandler();

        // 3.校验
        User user = handler.check(signParamDTO);

        // 4.DAO -> DTO 并返回用户信息
        return BeanCopierUtils.copyBean(user, SignUserDTO.class);
    }
}
