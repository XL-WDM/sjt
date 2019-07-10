package com.stj.business.strategy.sign.mode.impl;

import com.alibaba.fastjson.JSONObject;
import com.stj.business.api.dto.req.SignParamDTO;
import com.stj.business.entity.User;
import com.stj.business.entity.UserOauths;
import com.stj.business.mapper.UserOauthsMapper;
import com.stj.business.strategy.sign.mode.SignModeHandler;
import com.stj.common.base.constant.BaseConstant;
import com.stj.common.utils.CheckObjects;
import com.stj.wechat.entity.WxOauthAccessToken;
import com.stj.wechat.mapper.WxOauthAccessTokenMapper;
import com.stj.wechat.service.IWxOauthService;
import com.stj.wechat.vo.res.WxAccessTokenVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @author: yilan.hu
 * @data: 2019/7/8
 */
@Slf4j
@Component
public class WechatModeSignHandler implements SignModeHandler {

    @Autowired
    private WxOauthAccessTokenMapper wxOauthAccessTokenMapper;

    @Autowired
    private IWxOauthService iWxOauthService;

    @Autowired
    private UserOauthsMapper userOauthsMapper;

    @Override
    public User check(SignParamDTO signParamDTO) {
        // 1.参数校验
        String accessToken = signParamDTO.getAccessToken();
        CheckObjects.isEmpty(accessToken, "授权接口调用凭证不能为空");

        // 2.查询凭证信息
        WxOauthAccessToken wxOauthAccessToken = wxOauthAccessTokenMapper.selectByAccessToken(accessToken);
        CheckObjects.isNull(wxOauthAccessToken, "授权凭证不存在");

        // 3.处理凭证刷新
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime expiresTime = wxOauthAccessToken.getExpiresTime();
        if (expiresTime.compareTo(now) < 0) {
            WxAccessTokenVO wxAccessTokenVO = iWxOauthService.refreshoauthAccessToken(wxOauthAccessToken.getRefreshToken());
            CheckObjects.isNull(wxAccessTokenVO, "授权凭证更新失败");
            // 更新凭证有效期
            wxOauthAccessToken.setExpiresTime(now
                    .plusSeconds(wxAccessTokenVO.getExpires_in() - BaseConstant.Second.MINUTE));
            wxOauthAccessToken.setRefreshDate(now);
            wxOauthAccessToken.updateById();
        }

        // 4.查询用户
        UserOauths userOauths = new UserOauths();
        userOauths.setOauthId(wxOauthAccessToken.getOpenid());


        return null;
    }
}
