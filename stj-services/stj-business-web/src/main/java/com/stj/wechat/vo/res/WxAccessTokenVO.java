package com.stj.wechat.vo.res;

import lombok.Data;

/**
 * @author: yilan.hu
 * @data: 2019/7/9
 */
@Data
public class WxAccessTokenVO extends WxResultVO {

    /**
     * 获取到的凭证
     */
    private String access_token;

    /**
     * 凭证有效时间，单位：秒
     */
    private Long expires_in;

    /**
     * 用户刷新access_token
     */
    private String refresh_token;

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
