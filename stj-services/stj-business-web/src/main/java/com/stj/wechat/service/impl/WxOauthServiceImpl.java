package com.stj.wechat.service.impl;

import com.stj.common.exceptions.GlobalException;
import com.stj.common.utils.CheckObjects;
import com.stj.wechat.config.WxBaseInfo;
import com.stj.wechat.constant.WechatConstant;
import com.stj.wechat.service.IWxOauthService;
import com.stj.wechat.vo.res.WxAccessTokenVO;
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

    @Override
    public WxAccessTokenVO getOauthAccessToken(String code) {
        // 1.参数校验
        CheckObjects.isEmpty(code, "网页授权code获取失败");

        // 2.请求
        String url = WechatConstant.OAUTH_ACCESS_TOKEN_GET_URL
                .replace("APPID", wxBaseInfo.getAppId())
                .replace("SECRET", wxBaseInfo.getAppsecret())
                .replace("CODE", code);
        ResponseEntity<WxAccessTokenVO> entity = restTemplate.getForEntity(url, WxAccessTokenVO.class);
        if (HttpStatus.OK != entity.getStatusCode()) {
            log.error("# 网页授权AccessToken获取失败(调用微信服务响应失败) -> status: {}", entity.getStatusCodeValue());
            throw new GlobalException("网页授权AccessToken获取失败");
        }

        // 3.获取body
        WxAccessTokenVO wxAccessTokenVO = entity.getBody();
        if (!wxAccessTokenVO.isSuccess()) {
            log.error("# 网页授权AccessToken获取失败 -> errcode: {}, errmsg: {}", wxAccessTokenVO.getErrcode(), wxAccessTokenVO.getErrmsg());
            throw new GlobalException("网页授权AccessToken获取失败");
        }

        return wxAccessTokenVO;
    }
}
