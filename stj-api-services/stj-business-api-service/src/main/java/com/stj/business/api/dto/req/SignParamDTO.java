package com.stj.business.api.dto.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author: yilan.hu
 * @data: 2019/7/8
 */
@Data
public class SignParamDTO {

    /**
     * 用户名
     */
    @ApiModelProperty("用户名")
    private String username;

    /**
     * 邮箱
     */
    @ApiModelProperty("邮箱")
    private String email;

    /**
     * 手机号
     */
    @ApiModelProperty("手机号")
    private String phone;

    /**
     * 密码
     */
    @ApiModelProperty(value = "密码", required = true)
    private String password;

    @ApiModelProperty(value = "网页授权接口调用凭证")
    private String accessToken;

    /**
     * 登录方式(1-密码登录, 2-短信验证登录, 3-邮箱登录, 4-微信登录, 5-QQ登录, 6.微博登录)
     */
    @ApiModelProperty(value = "登录方式(1-密码登录, 2-短信验证登录, 3-邮箱登录, 4-微信登录, 5-QQ登录, 6.微博登录)", required = true)
    private String signMode;
}
