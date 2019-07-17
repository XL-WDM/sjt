package com.sjt.business.strategy.sign.mode.impl;

import com.sjt.business.api.dto.req.SignParamDTO;
import com.sjt.business.constant.DataBaseConstant;
import com.sjt.business.entity.User;
import com.sjt.business.entity.UserOauths;
import com.sjt.business.entity.WxOauthAccessToken;
import com.sjt.business.entity.WxSnsapiUserInfo;
import com.sjt.business.mapper.UserMapper;
import com.sjt.business.mapper.UserOauthsMapper;
import com.sjt.business.mapper.WxOauthAccessTokenMapper;
import com.sjt.business.mapper.WxSnsapiUserInfoMapper;
import com.sjt.business.strategy.sign.mode.SignModeHandler;
import com.sjt.common.base.constant.BaseConstant;
import com.sjt.common.utils.BeanCopierUtils;
import com.sjt.common.utils.CheckObjects;
import com.sjt.wechat.api.dto.res.WxAccessTokenDTO;
import com.sjt.wechat.api.dto.res.WxSnsapiUserInfoDTO;
import com.sjt.wechat.service.IWxOauthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * @author: yilan.hu
 * @data: 2019/7/8
 */
@Slf4j
@Service
public class WechatModeSignHandler implements SignModeHandler {

    @Autowired
    private WxOauthAccessTokenMapper wxOauthAccessTokenMapper;

    @Autowired
    private IWxOauthService iWxOauthService;

    @Autowired
    private UserOauthsMapper userOauthsMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private WxSnsapiUserInfoMapper wxSnsapiUserInfoMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public UserModel check(SignParamDTO signParamDTO) {
        // 1.参数校验
        String token = signParamDTO.getToken();
        CheckObjects.isEmpty(token, "授权凭证不能为空");

        // 2.查询凭证信息
        WxOauthAccessToken wxOauthAccessToken = wxOauthAccessTokenMapper.selectByRefreshToken(token);
        CheckObjects.isNull(wxOauthAccessToken, "授权凭证不存在");
        // 2-1.处理凭证刷新
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime expiresTime = wxOauthAccessToken.getExpiresTime();
        if (expiresTime.compareTo(now) < 0) {
            WxAccessTokenDTO wxAccessTokenDTO = iWxOauthService.refreshoauthAccessToken(wxOauthAccessToken.getRefreshToken());
            CheckObjects.isNull(wxAccessTokenDTO, "授权凭证更新失败");
            // 2-2.DTO -> DAO
            WxOauthAccessToken woat = BeanCopierUtils.copyBean(wxAccessTokenDTO, WxOauthAccessToken.class);
            // 2-3更新凭证有效期
            woat.setId(wxOauthAccessToken.getId());
            woat.setExpiresTime(now.plusSeconds(wxAccessTokenDTO.getExpiresIn() - BaseConstant.Second.MINUTE));
            woat.setRefreshDate(now);
            woat.updateById();
        }

        // 3.查询授权信息
        UserOauths userOauths = userOauthsMapper.selectOneByOauthIdAndStatus(wxOauthAccessToken.getOpenid());
        Integer maxAge = BaseConstant.Second.DAY * 30;
        if (userOauths != null) {
            // 3-1.获取用户信息
            User user = userMapper.selectById(userOauths.getUserId());
            CheckObjects.predicate(user.getStatus(),
                    s -> BaseConstant.Status.NO.getCode().equals(s), "用户已冻结");
            return new UserModel(user, maxAge);
        } else {
            // 3-2-1.获取微信用户信息
            WxSnsapiUserInfoDTO wxSnsapiUserInfoDTO = iWxOauthService
                    .getWxSnsapiUserInfo(wxOauthAccessToken.getAccessToken(),
                    wxOauthAccessToken.getOpenid());
            CheckObjects.isNull(wxSnsapiUserInfoDTO, "微信用户信息获取为空");
            // 3-2-2.新增微信用户信息
            // 3-2-2-1.查询微信用户信息
            WxSnsapiUserInfo wxSnsapiUserInfo = wxSnsapiUserInfoMapper.selectByOpenid(wxSnsapiUserInfoDTO.getOpenid(),
                    wxSnsapiUserInfoDTO.getUnionid());
            if (wxSnsapiUserInfo != null) {
                // 3-2-2-2.更新用户信息
                // DTO -> DAO
                WxSnsapiUserInfo sui = BeanCopierUtils.copyBean(wxSnsapiUserInfoDTO, WxSnsapiUserInfo.class);
                sui.setId(wxSnsapiUserInfo.getId());
                sui.updateById();
            } else {
                // 3-2-2-3.新增微信用户信息
                // DTO -> DAO
                wxSnsapiUserInfo = BeanCopierUtils.copyBean(wxSnsapiUserInfoDTO, WxSnsapiUserInfo.class);
                wxSnsapiUserInfo.insert();
            }
            // 3-2-3.新增用户信息
            User user = new User();
            user.setFaceUrl(wxSnsapiUserInfo.getHeadimgurl());
            user.setNickname(wxSnsapiUserInfo.getNickname());
            user.setSex(wxSnsapiUserInfo.getSex());
            user.insert();

            // 3-2-3.新增授权信息
            userOauths = new UserOauths();
            userOauths.setUserId(user.getId());
            userOauths.setOauthId(wxSnsapiUserInfo.getOpenid());
            userOauths.setUnionId(wxSnsapiUserInfo.getUnionid());
            userOauths.setOauthType(DataBaseConstant.OauthType.WX_PUBLIC_NUMBER.getCode());
            userOauths.insert();

            return new UserModel(user, maxAge);
        }
    }
}
