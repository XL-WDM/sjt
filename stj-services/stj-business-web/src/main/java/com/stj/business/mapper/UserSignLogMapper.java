package com.stj.business.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.stj.business.entity.UserSignLog;

/**
 * @author: yilan.hu
 * @data: 2019/7/9
 */
public interface UserSignLogMapper extends BaseMapper<UserSignLog> {
    /**
     * 通过 token 查询记录
     * @param token
     * @return
     */
    UserSignLog selectSignLog(String token);
}
