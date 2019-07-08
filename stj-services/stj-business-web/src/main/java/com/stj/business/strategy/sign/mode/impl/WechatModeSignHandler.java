package com.stj.business.strategy.sign.mode.impl;

import com.stj.business.api.dto.req.SignParamDTO;
import com.stj.business.entity.User;
import com.stj.business.strategy.sign.mode.SignModeHandler;

/**
 * @author: yilan.hu
 * @data: 2019/7/8
 */
public class WechatModeSignHandler implements SignModeHandler {

    @Override
    public User check(SignParamDTO signParamDTO) {
        return null;
    }
}
