package com.sjt.business.strategy.sign.mode.impl;

import com.sjt.business.api.dto.req.SignParamDTO;
import com.sjt.business.strategy.sign.mode.SignModeHandler;
import com.sjt.common.utils.CheckObjects;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author: yilan.hu
 * @data: 2019/7/8
 */
@Slf4j
@Service
public class SmsModeSignHandler implements SignModeHandler {

    @Override
    public UserModel check(SignParamDTO signParamDTO) {
        // 1.参数校验
        CheckObjects.isNull(null, "暂不支持短信验证码登陆");

        return null;
    }
}
