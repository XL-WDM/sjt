package com.sjt.business.strategy.sign.mode.impl;

import com.sjt.business.api.dto.req.SignParamDTO;
import com.sjt.business.constant.EncryptionSlotConstant;
import com.sjt.business.entity.User;
import com.sjt.business.mapper.UserMapper;
import com.sjt.business.strategy.sign.mode.SignModeHandler;
import com.sjt.common.exceptions.GlobalException;
import com.sjt.common.utils.BeanCopierUtils;
import com.sjt.common.utils.CheckObjects;
import com.sjt.common.utils.CryptoEncryptUtils;
import com.sjt.common.utils.MD5Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * @author: yilan.hu
 * @data: 2019/7/8
 */
@Slf4j
@Service
public class PasswordModeSignHandler implements SignModeHandler {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserModel check(SignParamDTO signParamDTO) {
        // 1.校验
        String password = signParamDTO.getPassword();
        String username = signParamDTO.getUsername();
        String phone = signParamDTO.getPhone();
        String email = signParamDTO.getEmail();

        CheckObjects.isEmpty(password, "请输入您密码");
        try {
            password = CryptoEncryptUtils.aesDecrypt(password);
            CheckObjects.isEmpty(password, "密码密文格式不正确");
        } catch (Exception e) {
            throw new GlobalException("密码密文格式不正确");
        }

        String pwd = MD5Utils.getMD5(password, EncryptionSlotConstant.PASSWORD_SLOT);
        CheckObjects.isEmpty(pwd, "密码不正确");

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
        // DTO -> Entity
        User user = BeanCopierUtils.copyBean(signParamDTO, User.class);
        user = userMapper.selectUserBySign(user);

        CheckObjects.isNull(user, "用户不存在");

        // 3.密码验证
        CheckObjects.predicate(user, u -> !u.getPassword().equals(pwd), "密码输入有误");

        return new UserModel(user, Integer.MAX_VALUE);
    }
}
