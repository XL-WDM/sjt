package com.sjt.business.service.impl;

import com.sjt.business.api.dto.req.SignParamDTO;
import com.sjt.business.api.dto.res.SignUserDTO;
import com.sjt.business.constant.DataBaseConstant;
import com.sjt.business.entity.User;
import com.sjt.business.entity.UserSignLog;
import com.sjt.business.service.IUserService;
import com.sjt.business.strategy.sign.mode.SignModeHandler;
import com.sjt.business.web.config.WebUserContext;
import com.sjt.common.base.constant.BaseConstant;
import com.sjt.common.base.constant.ResultConstant;
import com.sjt.common.utils.BeanCopierUtils;
import com.sjt.common.utils.CheckObjects;
import com.sjt.common.utils.ResponseUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @author: yilan.hu
 * @data: 2019/7/8
 */
@Slf4j
@Service
public class UserServiceImpl implements IUserService {

    @Override
    public SignUserDTO sign(SignParamDTO signParamDTO, HttpServletResponse response) {
        // 1.参数校验
        CheckObjects.isNull(signParamDTO, ResultConstant.PARAMETERS_CANNOT_BE_NULL);
        String signMode = signParamDTO.getSignMode();
        CheckObjects.isEmpty(signMode, "请选择登录方式");
        DataBaseConstant.SignMode signModeEnum = DataBaseConstant.SignMode.find(signMode);
        CheckObjects.isNull(signModeEnum, "登录方式有误");

        // 2.获取处理器
        SignModeHandler handler = signModeEnum.getHandler();

        // 3.校验
        SignModeHandler.UserModel userModel = handler.check(signParamDTO);

        Integer maxAge = userModel.getMaxAge();
        User user = userModel.getUser();

        CheckObjects.isNull(user, "用户可能不存在");

        // 4.DAO -> DTO 并
        SignUserDTO signUserDTO = BeanCopierUtils.copyBean(user, SignUserDTO.class);

        // 5.生成 token
        String token = UUID.randomUUID().toString().replaceAll(BaseConstant.Character.BAR, BaseConstant.Character.UNDERLINE);

        // 6.存入数据库
        UserSignLog userSignLog = new UserSignLog();
        userSignLog.setUserId(user.getId());
        userSignLog.setToken(token);
        userSignLog.setExpirationTime(LocalDateTime.now().plusSeconds(maxAge));
        userSignLog.insert();

        // 7.设置cookie
        ResponseUtils.setCookie(response, WebUserContext.USER_COOKIE, token, maxAge);

        // 8.设置缓存
        WebUserContext.instance(user);

        // 9.返回用户信息
        return signUserDTO;
    }
}
