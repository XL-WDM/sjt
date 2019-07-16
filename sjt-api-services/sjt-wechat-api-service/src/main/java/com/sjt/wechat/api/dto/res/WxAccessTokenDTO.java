package com.sjt.wechat.api.dto.res;

import lombok.Data;

/**
 * @author: yilan.hu
 * @data: 2019/7/16
 */
@Data
public class WxAccessTokenDTO {

    /**
     * 获取到的凭证
     */
    private String accessToken;

    /**
     * 凭证有效时间，单位：秒
     */
    private Integer expiresIn;

    /**
     * 用户刷新access_token
     */
    private String refreshToken;

    /**
     * 用户唯一标识，请注意，在未关注公众号时，
     * 用户访问公众号的网页，也会产生一个用户和公众号唯一的OpenID
     */
    private String openid;

    /**
     * 用户授权的作用域，使用逗号分隔
     */
    private String scope;
}
