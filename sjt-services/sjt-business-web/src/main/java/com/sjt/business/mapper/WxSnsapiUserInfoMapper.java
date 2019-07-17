package com.sjt.business.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.sjt.business.entity.WxSnsapiUserInfo;
import org.apache.ibatis.annotations.Param;

/**
 * @author: yilan.hu
 * @data: 2019/7/16
 */
public interface WxSnsapiUserInfoMapper extends BaseMapper<WxSnsapiUserInfo> {

    /**
     * 通过 openid 或者 unionid 查询微信用户信息
     * @param openid
     * @param unionid
     * @return
     */
    WxSnsapiUserInfo selectByOpenidOrUnionid(@Param("openid") String openid, @Param("unionid") String unionid);

    /**
     * 通过 openid 查询微信用户信息
     * @param openid
     * @return
     */
    WxSnsapiUserInfo selectByOpenid(String openid);
}
