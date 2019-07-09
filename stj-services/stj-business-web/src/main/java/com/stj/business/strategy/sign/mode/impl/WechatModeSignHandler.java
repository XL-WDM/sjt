package com.stj.business.strategy.sign.mode.impl;

import com.stj.business.api.dto.req.SignParamDTO;
import com.stj.business.entity.User;
import com.stj.business.strategy.sign.mode.SignModeHandler;
import com.stj.common.utils.CheckObjects;

/**
 * @author: yilan.hu
 * @data: 2019/7/8
 */
public class WechatModeSignHandler implements SignModeHandler {

    @Override
    public User check(SignParamDTO signParamDTO) {
        CheckObjects.isEmpty(signParamDTO.getAccessToken(), "网页授权接口调用凭证不能为空");



        return null;
    }
}
