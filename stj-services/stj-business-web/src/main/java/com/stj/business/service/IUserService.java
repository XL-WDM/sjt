package com.stj.business.service;

import com.stj.business.api.dto.req.SignParamDTO;
import com.stj.business.api.dto.res.SignUserDTO;

import javax.servlet.http.HttpServletResponse;

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
    SignUserDTO sign(SignParamDTO signParamDTO, HttpServletResponse response);
}
