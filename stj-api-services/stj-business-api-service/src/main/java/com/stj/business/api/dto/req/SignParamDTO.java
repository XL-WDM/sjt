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
    @ApiModelProperty("密码")
    private String password;

    /**
     * 登陆方式(1-密码登录, 2-短信验证登录, 3-邮箱登陆, 4-微信登陆, 5-QQ登陆, 6.微博登陆)
     */
    @ApiModelProperty("登陆方式(1-密码登录, 2-短信验证登录, 3-邮箱登陆, 4-微信登陆, 5-QQ登陆, 6.微博登陆)")
    private String signMode;
}
