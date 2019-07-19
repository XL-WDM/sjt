package com.sjt.business.api.dto.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author: yilan.hu
 * @data: 2019/7/8
 */
@ApiModel("授权登录 Request params")
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
    @ApiModelProperty(value = "密码")
    private String password;

    /**
     * 网页授权接口调用凭证
     */
    @ApiModelProperty(value = "网页授权接口调用凭证")
    private String token;

    /**
     * 登录凭证code
     */
    @ApiModelProperty(value = "登录凭证code")
    private String code;

    /**
     * 登录方式(1-密码登录, 2-短信验证登录, 3-邮箱登录, 4-QQ登录, 5-微博登录, 6-微信小程序, 7-微信公众号登录)
     */
    @ApiModelProperty(value = "登录方式(1-密码登录, 2-短信验证登录, 3-邮箱登录, 4-QQ登录, 5-微博登录, 6-微信小程序, 7-微信公众号登录)", required = true)
    private String signMode;
}
