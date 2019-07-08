package com.stj.business.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.stj.business.entity.User;

/**
 * @author: yilan.hu
 * @data: 2019/7/8
 */
public interface UserMapper extends BaseMapper<User> {
    /**
     * 通过用户名、手机号、邮箱任意一种查询
     * @param user
     * @return
     */
    User selectUserBySign(User user);
}
