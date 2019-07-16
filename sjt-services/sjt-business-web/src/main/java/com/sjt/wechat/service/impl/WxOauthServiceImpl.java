package com.sjt.wechat.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.sjt.business.mapper.WxOauthAccessTokenMapper;
import com.sjt.common.exceptions.GlobalException;
import com.sjt.common.utils.BeanCopierUtils;
import com.sjt.common.utils.CheckObjects;
import com.sjt.wechat.api.dto.res.WxAccessTokenDTO;
import com.sjt.wechat.api.dto.res.WxAppletSessionKeyDTO;
import com.sjt.wechat.api.dto.res.WxSnsapiUserInfoDTO;
import com.sjt.wechat.config.WxBaseInfo;
import com.sjt.wechat.constant.WechatConstant;
import com.sjt.wechat.service.IWxOauthService;
import com.sjt.wechat.vo.res.WxAccessTokenVO;
import com.sjt.wechat.vo.res.WxAppletSessionKeyVO;
import com.sjt.wechat.vo.res.WxSnsapiUserInfoVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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
    public WxAccessTokenDTO getOauthAccessToken(String code) {
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
        CheckObjects.isNull(wxAccessTokenVO, "微信网页授权信息获取为空");
        if (!wxAccessTokenVO.isSuccess()) {
            log.error("# 微信网页授权: AccessToken获取失败 -> errcode: {}, errmsg: {}",
                    wxAccessTokenVO.getErrcode(),
                    wxAccessTokenVO.getErrmsg());
            throw new GlobalException("微信网页授权: AccessToken获取失败");
        }

        // 4.VO -> DTO
        return wxAccessTokenVOToDTO(wxAccessTokenVO);
    }

    @Override
    public WxAccessTokenDTO refreshoauthAccessToken(String refreshToken) {
        CheckObjects.isEmpty(refreshToken, "refresh_token 不能为空");

        // 4.刷新url
        String url = WechatConstant.REFRESH_TOKEN_GET_URL
                .replace("APPID", wxBaseInfo.getAppId())
                .replace("REFRESH_TOKEN", refreshToken);
        try {
            // 4-1.请求微信方获取微信网页授权信息
            String result = restTemplate.getForObject(url, String.class);
            WxAccessTokenVO wxAccessTokenVO = JSONObject.parseObject(result, WxAccessTokenVO.class);
            // 4-2.VO -> DTO
            return wxAccessTokenVOToDTO(wxAccessTokenVO);
        } catch (Exception e) {
            log.error("# 微信网页授权: AccessToken刷新失败 -> {}", e.getMessage(), e);
            return null;
        }
    }

    @Override
    public WxSnsapiUserInfoDTO getWxSnsapiUserInfo(String accessToken, String openid) {
        // 1.参数校验
        CheckObjects.isEmpty(accessToken, "授权凭证不能为空");
        CheckObjects.isEmpty(openid, "用户的唯一标识 openid 不能为空");

        // 2.请求
        String url = WechatConstant.PULL_SNSAPI_USERINFO_GET_URL
                .replace("ACCESS_TOKEN", accessToken)
                .replace("OPENID", openid);

        // 2-1.请求微信方获取微信用户信息
        ResponseEntity<String> entity = restTemplate.getForEntity(url, String.class);
        if (HttpStatus.OK != entity.getStatusCode()) {
            log.error("# 微信用户信息查询: (调用微信服务响应失败) -> status: {}", entity.getStatusCodeValue());
            throw new GlobalException("微信用户信息查询失败");
        }
        WxSnsapiUserInfoVO wxSnsapiUserInfoVO = JSONObject.parseObject(entity.getBody(), WxSnsapiUserInfoVO.class);
        CheckObjects.isNull(wxSnsapiUserInfoVO, "微信用户信息获取为空");
        if (!wxSnsapiUserInfoVO.isSuccess()) {
            log.error("# 微信用户信息查询失败 -> errcode: {}, errmsg: {}",
                    wxSnsapiUserInfoVO.getErrcode(),
                    wxSnsapiUserInfoVO.getErrmsg());
            throw new GlobalException("微信用户信息查询失败");
        }

        // 3.VO -> DTO
        return BeanCopierUtils.copyBean(wxSnsapiUserInfoVO, WxSnsapiUserInfoDTO.class);
    }

    @Override
    public WxAppletSessionKeyDTO getWxAppletSeesionKey(String code) {
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

        // 2-1.请求微信方获取小程序授权信息
        WxAppletSessionKeyVO wxAppletSessionKeyVO = JSONObject.parseObject(entity.getBody(), WxAppletSessionKeyVO.class);
        CheckObjects.isNull(wxAppletSessionKeyVO, "微信网页授权信息获取为空");
        if (!wxAppletSessionKeyVO.isSuccess()) {
            log.error("# 微信小程序登陆: SessionKey获取失败 -> errcode: {}, errmsg: {}",
                    wxAppletSessionKeyVO.getErrcode(),
                    wxAppletSessionKeyVO.getErrmsg());
            throw new GlobalException("微信网页授权: SessionKey获取失败");
        }

        // VO -> DTO
        WxAppletSessionKeyDTO wxAppletSessionKeyDTO = BeanCopierUtils.copyBean(wxAppletSessionKeyVO, WxAppletSessionKeyDTO.class);
        wxAppletSessionKeyDTO.setSessionKey(wxAppletSessionKeyVO.getSession_key());
        return wxAppletSessionKeyDTO;
    }

    /**
     * VO -> DTO
     * @param wxAccessTokenVO
     * @return
     */
    private WxAccessTokenDTO wxAccessTokenVOToDTO(WxAccessTokenVO wxAccessTokenVO) {
        WxAccessTokenDTO wxAccessTokenDTO = new WxAccessTokenDTO();
        wxAccessTokenDTO.setAccessToken(wxAccessTokenVO.getAccess_token());
        wxAccessTokenDTO.setExpiresIn(wxAccessTokenVO.getExpires_in());
        wxAccessTokenDTO.setRefreshToken(wxAccessTokenVO.getRefresh_token());
        wxAccessTokenDTO.setOpenid(wxAccessTokenVO.getOpenid());
        wxAccessTokenDTO.setScope(wxAccessTokenVO.getScope());
        return wxAccessTokenDTO;
    }
}
