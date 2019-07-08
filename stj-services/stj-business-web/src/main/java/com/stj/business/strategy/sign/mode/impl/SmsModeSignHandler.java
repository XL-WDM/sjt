package com.stj.business.strategy.sign.mode.impl;

import com.stj.business.api.dto.req.SignParamDTO;
import com.stj.business.strategy.sign.mode.SignModeHandler;

/**
 * @author: yilan.hu
 * @data: 2019/7/8
 */
public class SmsModeSignHandler implements SignModeHandler {

    @Override
    public boolean handler(SignParamDTO signParamDTO) {
        return false;
    }
}
