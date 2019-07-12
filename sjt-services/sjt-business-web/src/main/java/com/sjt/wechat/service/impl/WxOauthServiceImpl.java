package com.sjt.wechat.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.sjt.common.base.constant.BaseConstant;
import com.sjt.wechat.entity.WxOauthAccessToken;
import com.sjt.wechat.mapper.WxOauthAccessTokenMapper;
import com.sjt.common.exceptions.GlobalException;
import com.sjt.common.utils.CheckObjects;
import com.sjt.wechat.config.WxBaseInfo;
import com.sjt.wechat.constant.WechatConstant;
import com.sjt.wechat.service.IWxOauthService;
import com.sjt.wechat.vo.res.WxAccessTokenVO;
import com.sjt.wechat.vo.res.WxAppletSessionKeyVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

/**
 * @author: yilan.hu
 * @data: 2019/7/9
 */
@Slf4j
@Service
public class WxOauthServiceImpl implements IWxOauthService {

    @Autowired
    private WxBaseInfo wxBaseInfo;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private WxOauthAccessTokenMapper wxOauthAccessTokenMapper;

    @Override
    public WxAccessTokenVO getOauthAccessToken(String code) {
        // 1.参数校验
        CheckObjects.isEmpty(code, "微信网页授权: code获取失败");

        // 2.请求
        String url = WechatConstant.OAUTH_ACCESS_TOKEN_GET_URL
                .replace("APPID", wxBaseInfo.getAppId())
                .replace("SECRET", wxBaseInfo.getAppsecret())
                .replace("CODE", code);
        ResponseEntity<String> entity = restTemplate.getForEntity(url, String.class);
        if (HttpStatus.OK != entity.getStatusCode()) {
            log.error("# 微信网页授权: AccessToken获取失败(调用微信服务响应失败) -> status: {}", entity.getStatusCodeValue());
            throw new GlobalException("微信网页授权: AccessToken获取失败");
        }

        // 3.获取body
        WxAccessTokenVO wxAccessTokenVO = JSONObject.parseObject(entity.getBody(), WxAccessTokenVO.class);
        if (!wxAccessTokenVO.isSuccess()) {
            log.error("# 微信网页授权: AccessToken获取失败 -> errcode: {}, errmsg: {}",
                    wxAccessTokenVO.getErrcode(),
                    wxAccessTokenVO.getErrmsg());
            throw new GlobalException("微信网页授权: AccessToken获取失败");
        }

        // VO -> DAO
        WxOauthAccessToken wxOauthAccessToken = oauthAccessTokenVoToDao(wxAccessTokenVO);
        wxOauthAccessToken.setExpiresTime(LocalDateTime.now()
                .plusSeconds(wxAccessTokenVO.getExpires_in() - BaseConstant.Second.MINUTE));
        wxOauthAccessToken.insert();

        return wxAccessTokenVO;
    }

    @Override
    public WxAccessTokenVO refreshoauthAccessToken(String refreshToken) {
        CheckObjects.isEmpty(refreshToken, "refresh_token 不能为空");

        // 4.刷新url
        String url = WechatConstant.REFRESH_TOKEN_GET_URL
                .replace("APPID", wxBaseInfo.getAppId())
                .replace("REFRESH_TOKEN", refreshToken);
        try {
            String result = restTemplate.getForObject(url, String.class);
            return JSONObject.parseObject(result, WxAccessTokenVO.class);
        } catch (Exception e) {
            log.error("# 微信网页授权: AccessToken刷新失败 -> {}", e.getMessage(), e);
            return null;
        }
    }

    @Override
    public WxAppletSessionKeyVO getWxAppletSeesionKey(String code) {
        // 1.参数校验
        CheckObjects.isEmpty(code, "登录凭证code不能为空");

        // 2.请求
        String url = WechatConstant.APPLET_CODE_2_SESSION_GET_URL
                .replace("APPID", wxBaseInfo.getAppletId())
                .replace("SECRET", wxBaseInfo.getAppletAppsecret())
                .replace("JSCODE", code);

        ResponseEntity<String> entity = restTemplate.getForEntity(url, String.class);
        if (HttpStatus.OK != entity.getStatusCode()) {
            log.error("# 微信小程序登陆: SessionKey获取失败(调用微信服务响应失败) -> status: {}", entity.getStatusCodeValue());
            throw new GlobalException("微信网页授权: SessionKey获取失败");
        }

        WxAppletSessionKeyVO wxAppletSessionKeyVO = JSONObject.parseObject(entity.getBody(), WxAppletSessionKeyVO.class);
        if (!wxAppletSessionKeyVO.isSuccess()) {
            log.error("# 微信小程序登陆: SessionKey获取失败 -> errcode: {}, errmsg: {}",
                    wxAppletSessionKeyVO.getErrcode(),
                    wxAppletSessionKeyVO.getErrmsg());
            throw new GlobalException("微信网页授权: SessionKey获取失败");
        }

        return wxAppletSessionKeyVO;
    }

    private WxOauthAccessToken oauthAccessTokenVoToDao(WxAccessTokenVO wxAccessTokenVO) {
        WxOauthAccessToken wxOauthAccessToken = new WxOauthAccessToken();
        wxOauthAccessToken.setAccessToken(wxAccessTokenVO.getAccess_token());
        wxOauthAccessToken.setExpiresIn(wxAccessTokenVO.getExpires_in());
        wxOauthAccessToken.setRefreshToken(wxAccessTokenVO.getRefresh_token());
        wxOauthAccessToken.setOpenid(wxAccessTokenVO.getOpenid());
        wxOauthAccessToken.setScope(wxAccessTokenVO.getScope());
        return wxOauthAccessToken;
    }
}
