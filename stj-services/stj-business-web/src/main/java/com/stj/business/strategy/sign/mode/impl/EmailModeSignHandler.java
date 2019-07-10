package com.stj.business.strategy.sign.mode.impl;

import com.stj.business.api.dto.req.SignParamDTO;
import com.stj.business.strategy.sign.mode.SignModeHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author: yilan.hu
 * @data: 2019/7/10
 */
@Slf4j
@Component
public class EmailModeSignHandler implements SignModeHandler {

    @Override
    public UserModel check(SignParamDTO signParamDTO) {
        return null;
    }
}
