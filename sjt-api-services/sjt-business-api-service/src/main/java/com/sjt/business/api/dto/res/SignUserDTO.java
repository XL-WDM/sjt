package com.sjt.business.api.dto.res;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author: yilan.hu
 * @data: 2019/7/8
 */
@ApiModel("登录用户信息")
@Data
public class SignUserDTO {

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
     * 头像
     */
    @ApiModelProperty("头像")
    private String faceUrl;

    /**
     * 昵称
     */
    @ApiModelProperty("昵称")
    private String nickname;

    /**
     * 性别
     */
    @ApiModelProperty("性别")
    private String sex;

    /**
     * 生日
     */
    @ApiModelProperty("生日")
    private String birtrday;

    /**
     * 用户积分
     */
    @ApiModelProperty("用户积分")
    private Integer userPoint;

    /**
     * 会员级别(0-普通用户, 1-普通会员)
     */
    @ApiModelProperty("会员级别(0-普通用户, 1-普通会员)")
    private String memberLevel;
}
