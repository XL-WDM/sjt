package com.sjt.business.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.sjt.business.entity.WxOauthAccessToken;

/**
 * @author: yilan.hu
 * @data: 2019/7/10
 */
public interface WxOauthAccessTokenMapper extends BaseMapper<WxOauthAccessToken> {

    /**
     * 通过accessToken查询凭证信息
     * @param accessToken
     * @return
     */
    WxOauthAccessToken selectByAccessToken(String accessToken);
}
