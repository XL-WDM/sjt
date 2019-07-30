package com.sjt.business.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 用户授权信息
 * @author: yilan.hu
 * @data: 2019/7/10
 */
@Data
@TableName("t_user_oauths")
public class UserOauths extends Model<UserOauths> implements Serializable {

    private static final long serialVersionUID = -695867511230935988L;

    @TableId
    private Long id;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 授权id(比如: openid)
     */
    private String oauthId;

    /**
     * unionid
     */
    private String unionId;

    /**
     * 微信小程序sessionKey
     */
    private String sessionKey;

    /**
     * 授权状态(0-取消授权, 1-已授权)
     */
    private String status;

    /**
     * 授权类型(1-微信, 2-QQ, 3-微博)
     */
    private String oauthType;

    /**
     * 授权时间
     */
    private LocalDateTime oauthDate;

    /**
     * 更新时间
     */
    private LocalDateTime updateDate;

    @Override
    protected Serializable pkVal() {
        return id;
    }
}
