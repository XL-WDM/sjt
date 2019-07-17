package com.sjt.business.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.sjt.business.entity.UserOauths;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author: yilan.hu
 * @data: 2019/7/10
 */
public interface UserOauthsMapper extends BaseMapper<UserOauths> {

    /**
     * 通过oauthId 查询绑定状态的授权信息
     * @param oauthId
     * @param unionId
     * @return
     */
    List<UserOauths> selectOneByOauthIdAndUnionId(@Param("oauthId") String oauthId, @Param("unionId") String unionId);
}
