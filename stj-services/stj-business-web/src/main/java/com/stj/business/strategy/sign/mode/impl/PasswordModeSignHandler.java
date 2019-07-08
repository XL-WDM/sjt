package com.stj.business.strategy.sign.mode.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.stj.business.api.dto.req.SignParamDTO;
import com.stj.business.entity.User;
import com.stj.business.mapper.UserMapper;
import com.stj.business.strategy.sign.mode.SignModeHandler;
import com.stj.common.exceptions.GlobalException;
import com.stj.common.utils.CheckObjects;
import com.stj.common.utils.EncryptUtils;
import com.stj.common.utils.MD5Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

/**
 * @author: yilan.hu
 * @data: 2019/7/8
 */
@Slf4j
@Component
public class PasswordModeSignHandler implements SignModeHandler {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User check(SignParamDTO signParamDTO) {
        // 1.校验
        String password = signParamDTO.getPassword();
        String username = signParamDTO.getUsername();
        String phone = signParamDTO.getPhone();
        String email = signParamDTO.getEmail();

        CheckObjects.isEmpty(password, "密码不能为空");
        try {
            password = EncryptUtils.aesDecrypt(password);
            CheckObjects.isEmpty(password, "密码密文格式不正确");
        } catch (Exception e) {
            throw new GlobalException("密码密文格式不正确");
        }

        final Optional<String> pwd = Optional.of(MD5Utils.getMD5(password));

        boolean paramIsNull = true;
        if (!StringUtils.isEmpty(username)) {
            paramIsNull = false;
        }
        if (!StringUtils.isEmpty(phone)) {
            paramIsNull = false;
        }
        if (!StringUtils.isEmpty(signParamDTO.getEmail())) {
            paramIsNull = false;
        }

        CheckObjects.predicate(paramIsNull, b -> b, "必须在用户名、手机号、邮箱中填一个");

        // 2.查询
        EntityWrapper<User> wrapper = new EntityWrapper<>();
        if (!StringUtils.isEmpty(username)) {
            wrapper.eq("USERNAME", username);
        }
        if (!StringUtils.isEmpty(phone)) {
            wrapper.or()
                   .eq("PHONE", phone);
        }
        if (!StringUtils.isEmpty(email)) {
            wrapper.or()
                   .eq("EMAIL", email);
        }
        List<User> users = userMapper.selectList(wrapper);

        CheckObjects.isEmpty(users, "用户不存在");

        // 3.密码验证
        User user = users.get(0);
        CheckObjects.predicate(user, u -> !u.getPassword().equals(pwd.get()), "密码输入有误");

        return user;
    }
}
