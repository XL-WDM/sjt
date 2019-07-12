package com.sjt.business.service;

import com.sjt.business.api.dto.req.SignParamDTO;
import com.sjt.business.api.dto.res.SignUserDTO;

import javax.servlet.http.HttpServletResponse;

/**
 * @author: yilan.hu
 * @data: 2019/7/8
 */
public interface IUserService {

    /**
     * 登录
     * @param signParamDTO
     * @return
     */
    SignUserDTO sign(SignParamDTO signParamDTO, HttpServletResponse response);
}
