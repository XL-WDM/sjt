package com.stj.business.service.impl;

import com.stj.business.api.dto.req.SignParamDTO;
import com.stj.business.api.dto.res.SignUserDTO;
import com.stj.business.constant.DataBaseConstant;
import com.stj.business.entity.User;
import com.stj.business.entity.UserSignLog;
import com.stj.business.service.IUserService;
import com.stj.business.strategy.sign.mode.SignModeHandler;
import com.stj.business.web.config.WebUserContext;
import com.stj.common.base.constant.BaseConstant;
import com.stj.common.base.constant.ResultConstant;
import com.stj.common.utils.BeanCopierUtils;
import com.stj.common.utils.CheckObjects;
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
        CheckObjects.isEmpty(signMode, "登录方式不能为空");
        DataBaseConstant.SignMode signModeEnum = DataBaseConstant.SignMode.find(signMode);
        CheckObjects.isNull(signModeEnum, "登录方式格式不正确");

        // 2.获取处理器
        SignModeHandler handler = signModeEnum.getHandler();

        // 3.校验
        User user = handler.check(signParamDTO);

        // 4.DAO -> DTO 并
        SignUserDTO signUserDTO = BeanCopierUtils.copyBean(user, SignUserDTO.class);

        // 5.生成 token
        String token = UUID.randomUUID().toString().replaceAll(BaseConstant.Character.BAR, BaseConstant.Character.UNDERLINE);
        // 5-1.存入数据库
        UserSignLog userSignLog = new UserSignLog();
        userSignLog.setUserId(user.getId());
        userSignLog.setToken(token);
        int second = BaseConstant.Second.DAY * 30;
        userSignLog.setExpirationTime(LocalDateTime.now().plusSeconds(second * 1000));
        System.out.println(userSignLog.getExpirationTime().toString());
        userSignLog.insert();
        // 5-2.设置cookie
        Cookie cookie = new Cookie(WebUserContext.USER_COOKIE, token);
        cookie.setPath(BaseConstant.Character.SLASH);
        cookie.setMaxAge(second);
        response.addCookie(cookie);

        // 6.返回用户信息
        return signUserDTO;
    }
}
