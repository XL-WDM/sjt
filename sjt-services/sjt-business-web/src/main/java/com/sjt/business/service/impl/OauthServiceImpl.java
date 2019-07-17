package com.sjt.business.service.impl;

import com.sjt.business.api.dto.req.SignParamDTO;
import com.sjt.business.api.dto.res.SignUserDTO;
import com.sjt.business.constant.DataBaseConstant;
import com.sjt.business.entity.User;
import com.sjt.business.entity.UserSignLog;
import com.sjt.business.entity.WxOauthAccessToken;
import com.sjt.business.entity.WxSnsapiUserInfo;
import com.sjt.business.mapper.WxSnsapiUserInfoMapper;
import com.sjt.business.service.IOauthService;
import com.sjt.business.strategy.sign.mode.SignModeHandler;
import com.sjt.business.web.config.WebUserContext;
import com.sjt.common.base.constant.BaseConstant;
import com.sjt.common.base.constant.ResultConstant;
import com.sjt.common.utils.BeanCopierUtils;
import com.sjt.common.utils.CheckObjects;
import com.sjt.common.utils.ResponseUtils;
import com.sjt.wechat.api.dto.res.WxAccessTokenDTO;
import com.sjt.wechat.api.dto.res.WxSnsapiUserInfoDTO;
import com.sjt.wechat.service.IWxOauthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @author: yilan.hu
 * @data: 2019/7/8
 */
@Slf4j
@Service
public class OauthServiceImpl implements IOauthService {

    @Autowired
    private IWxOauthService iWxOauthService;

    @Autowired
    private WxSnsapiUserInfoMapper wxSnsapiUserInfoMapper;

    @Override
    public SignUserDTO sign(SignParamDTO signParamDTO, HttpServletResponse response) {
        // 1.参数校验
        CheckObjects.isNull(signParamDTO, ResultConstant.PARAMETERS_CANNOT_BE_NULL);
        String signMode = signParamDTO.getSignMode();
        CheckObjects.isEmpty(signMode, "请选择登录方式");
        DataBaseConstant.SignMode signModeEnum = DataBaseConstant.SignMode.find(signMode);
        CheckObjects.isNull(signModeEnum, "登录方式有误");

        // 2.获取处理器
        SignModeHandler handler = signModeEnum.getHandler();

        // 3.登陆处理
        SignModeHandler.UserModel userModel = handler.check(signParamDTO);

        Integer maxAge = userModel.getMaxAge();
        User user = userModel.getUser();

        CheckObjects.isNull(user, "用户可能不存在");

        // 4.DAO -> DTO
        SignUserDTO signUserDTO = BeanCopierUtils.copyBean(user, SignUserDTO.class);

        // 5.生成 token
        String token = UUID.randomUUID().toString().replaceAll(BaseConstant.Character.BAR, BaseConstant.Character.UNDERLINE);

        // 6.存入数据库
        UserSignLog userSignLog = new UserSignLog();
        userSignLog.setUserId(user.getId());
        userSignLog.setToken(token);
        userSignLog.setExpirationTime(LocalDateTime.now().plusSeconds(maxAge));
        userSignLog.insert();

        // 7.设置cookie
        ResponseUtils.setCookie(response, WebUserContext.USER_COOKIE, token, maxAge);

        // 8.设置缓存
        WebUserContext.instance(user);

        // 9.返回用户信息
        return signUserDTO;
    }

    @Override
    public WxAccessTokenDTO getOauthAccessToken(String code) {
        // 1.参数校验
        CheckObjects.isEmpty(code, "微信网页授权: code 获取为空");

        // 2.获取网页授权凭证
        WxAccessTokenDTO wxAccessTokenDTO = iWxOauthService.getOauthAccessToken(code);

        // 3.授权凭证入库
        // 3-1.DTO -> DAO
        WxOauthAccessToken wxOauthAccessToken = BeanCopierUtils.copyBean(wxAccessTokenDTO, WxOauthAccessToken.class);
        wxOauthAccessToken.setExpiresTime(LocalDateTime.now()
                .plusSeconds(wxOauthAccessToken.getExpiresIn() - BaseConstant.Second.MINUTE));
        wxOauthAccessToken.insert();

        // 4.授权用户信息入库
        swapWxUserInfo(wxOauthAccessToken.getAccessToken(), wxOauthAccessToken.getOpenid());

        return wxAccessTokenDTO;
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.NOT_SUPPORTED)
    public WxSnsapiUserInfo swapWxUserInfo(String accessToken, String openid) {
        // 1.参数校验
        CheckObjects.isEmpty(accessToken, "网页授权 accessToken 不能为空");
        CheckObjects.isEmpty(openid, "微信用户唯一标示 openid 不能为空");


        // 2.获取微信用户信息
        WxSnsapiUserInfoDTO wxSnsapiUserInfoDTO = iWxOauthService
                .getWxSnsapiUserInfo(accessToken, openid);
        CheckObjects.isNull(wxSnsapiUserInfoDTO, "微信用户信息获取为空");
        // 3.新增微信用户信息
        // 3-1.查询微信用户信息
        WxSnsapiUserInfo wxSnsapiUserInfo = wxSnsapiUserInfoMapper.selectByOpenidOrUnionid(wxSnsapiUserInfoDTO.getOpenid(),
                wxSnsapiUserInfoDTO.getUnionid());
        if (wxSnsapiUserInfo != null) {
            // 3-2.更新用户信息
            // DTO -> DAO
            WxSnsapiUserInfo sui = BeanCopierUtils.copyBean(wxSnsapiUserInfoDTO, WxSnsapiUserInfo.class);
            sui.setId(wxSnsapiUserInfo.getId());
            sui.updateById();
            return sui;
        } else {
            // 3-3.新增微信用户信息
            // DTO -> DAO
            wxSnsapiUserInfo = BeanCopierUtils.copyBean(wxSnsapiUserInfoDTO, WxSnsapiUserInfo.class);
            wxSnsapiUserInfo.insert();
            return wxSnsapiUserInfo;
        }
    }
}
