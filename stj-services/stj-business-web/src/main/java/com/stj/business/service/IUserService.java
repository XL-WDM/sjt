package com.stj.business.service;

import com.stj.business.api.dto.req.SignParamDTO;
import com.stj.business.api.dto.res.SignUserDTO;

/**
 * @author: yilan.hu
 * @data: 2019/7/8
 */
public interface IUserService {

    /**
     * 登陆
     * @param signParamDTO
     * @return
     */
    SignUserDTO sign(SignParamDTO signParamDTO);
}
