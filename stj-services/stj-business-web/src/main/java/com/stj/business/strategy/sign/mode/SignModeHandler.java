package com.stj.business.strategy.sign.mode;

import com.stj.business.api.dto.req.SignParamDTO;
import com.stj.business.entity.User;

/**
 * @author: yilan.hu
 * @data: 2019/7/8
 */
public interface SignModeHandler {

    /**
     * 验证
     * @param signParamDTO
     * @return
     */
    User check(SignParamDTO signParamDTO);
}
