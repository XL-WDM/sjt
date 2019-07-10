package com.stj.business.strategy.sign.mode.impl;

import com.stj.business.api.dto.req.SignParamDTO;
import com.stj.business.entity.User;
import com.stj.business.strategy.sign.mode.SignModeHandler;
import sun.misc.Signal;
import sun.misc.SignalHandler;

/**
 * @author: yilan.hu
 * @data: 2019/7/10
 */
public class WbmodeSignHandler implements SignModeHandler {

    @Override
    public User check(SignParamDTO signParamDTO) {
        return null;
    }
}
