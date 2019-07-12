package com.sjt.wechat.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author: yilan.hu
 * @data: 2019/7/10
 */
@Data
@TableName("t_wx_oauth_access_token")
public class WxOauthAccessToken extends Model<WxOauthAccessToken> implements Serializable {
    private static final long serialVersionUID = 4000269294336923030L;

    /**
     * 自增id
     */
    @TableId
    private Long id;

    /**
     * 网页授权接口调用凭证,注意：此access_token与基础支持的access_token不同
     */
    private String accessToken;

    /**
     * access_token接口调用凭证超时时间，单位（秒）
     */
    private Integer expiresIn;

    /**
     * 凭证过期时间
     */
    private LocalDateTime expiresTime;

    /**
     * 用户刷新access_token
     */
    private String refreshToken;

    /**
     * 用户唯一标识
     */
    private String openid;

    /**
     * 用户授权的作用域，使用逗号（,）分隔
     */
    private String scope;

    /**
     * 创建时间
     */
    private LocalDateTime createDate;

    /**
     * 刷新时间
     */
    private LocalDateTime refreshDate;

    @Override
    protected Serializable pkVal() {
        return id;
    }
}
