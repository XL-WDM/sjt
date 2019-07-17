package com.sjt.business.strategy.sign.mode.impl;

import com.sjt.business.api.dto.req.SignParamDTO;
import com.sjt.business.constant.DataBaseConstant;
import com.sjt.business.constant.EncryptionSlotConstant;
import com.sjt.business.entity.User;
import com.sjt.business.entity.UserOauths;
import com.sjt.business.entity.WxOauthAccessToken;
import com.sjt.business.entity.WxSnsapiUserInfo;
import com.sjt.business.mapper.UserMapper;
import com.sjt.business.mapper.UserOauthsMapper;
import com.sjt.business.mapper.WxOauthAccessTokenMapper;
import com.sjt.business.mapper.WxSnsapiUserInfoMapper;
import com.sjt.business.service.IOauthService;
import com.sjt.business.strategy.sign.mode.SignModeHandler;
import com.sjt.common.base.constant.BaseConstant;
import com.sjt.common.utils.AesEncryptUtils;
import com.sjt.common.utils.BeanCopierUtils;
import com.sjt.common.utils.CheckObjects;
import com.sjt.wechat.api.dto.res.WxAccessTokenDTO;
import com.sjt.wechat.service.IWxOauthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

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

    @Autowired
    private IOauthService iOauthService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public UserModel check(SignParamDTO signParamDTO) {
        // 1.参数校验
        String token = signParamDTO.getToken();
        CheckObjects.isEmpty(token, "授权凭据不能为空");

        token = AesEncryptUtils.decrypt(token, EncryptionSlotConstant.WX_REFRESH_TOKEN_SLOT);
        CheckObjects.isEmpty(token, "授权凭据有误");

        // 2.查询凭证信息
        WxOauthAccessToken wxOauthAccessToken = wxOauthAccessTokenMapper.selectByRefreshToken(token);
        CheckObjects.isNull(wxOauthAccessToken, "授权凭据不存在");
        // 2-1.处理凭证刷新
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime expiresTime = wxOauthAccessToken.getExpiresTime();
        if (expiresTime.compareTo(now) < 0) {
            WxAccessTokenDTO wxAccessTokenDTO = iWxOauthService.refreshoauthAccessToken(wxOauthAccessToken.getRefreshToken());
            CheckObjects.isNull(wxAccessTokenDTO, "授权凭据过期");
            // 2-2.DTO -> Entity
            WxOauthAccessToken woat = BeanCopierUtils.copyBean(wxAccessTokenDTO, WxOauthAccessToken.class);
            // 2-3更新凭证有效期
            woat.setId(wxOauthAccessToken.getId());
            woat.setExpiresTime(now.plusSeconds(wxAccessTokenDTO.getExpiresIn() - BaseConstant.Second.MINUTE));
            woat.setRefreshDate(now);
            woat.updateById();
        }

        // 3.获取微信用户信息
        WxSnsapiUserInfo wxSnsapiUserInfo = wxSnsapiUserInfoMapper.selectByOpenid(wxOauthAccessToken.getOpenid());
        if (wxSnsapiUserInfo == null) {
            wxSnsapiUserInfo = iOauthService.swapWxUserInfo(wxOauthAccessToken.getAccessToken(),
                    wxOauthAccessToken.getOpenid());
        }
        CheckObjects.isNull(wxSnsapiUserInfo, "获取微信用户信息失败");

        // 4.查询授权信息
        List<UserOauths> userOauths = userOauthsMapper.selectOneByOauthIdAndUnionId(wxSnsapiUserInfo.getOpenid(),
                wxSnsapiUserInfo.getUnionid());
        Integer maxAge = BaseConstant.Second.DAY * 30;
        User user = null;
        if (userOauths != null && !userOauths.isEmpty()) {
            // 4-1-1.获取用户信息
            Long userId = userOauths.get(0).getUserId();
            user = userMapper.selectById(userId);
            CheckObjects.predicate(user.getStatus(),
                    s -> BaseConstant.Status.NO.getCode().equals(s), "用户已冻结");

            // 4-1-2.APP微信开放平台和微信公众号号处理
            for (UserOauths userOauth : userOauths) {
                if (wxSnsapiUserInfo.getOpenid().equals(userOauth.getOauthId())) {
                    // 存在 wxSnsapiUserInfo.getOpenid() 的授权信息直接返回
                    return new UserModel(user, maxAge);
                }
            }
        } else {
            // 4-2.新增用户信息
            user = new User();
            user.setFaceUrl(wxSnsapiUserInfo.getHeadimgurl());
            user.setNickname(wxSnsapiUserInfo.getNickname());
            user.setSex(wxSnsapiUserInfo.getSex());
            user.insert();
        }

        // 5.新增授权信息
        UserOauths uo = new UserOauths();
        uo.setUserId(user.getId());
        uo.setOauthId(wxSnsapiUserInfo.getOpenid());
        uo.setUnionId(wxSnsapiUserInfo.getUnionid());
        uo.setOauthType(DataBaseConstant.OauthType.WX_PUBLIC_NUMBER.getCode());
        uo.insert();

        return new UserModel(user, maxAge);
    }
}
