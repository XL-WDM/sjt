package com.sjt.business.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 用户登陆日志
 * @author: yilan.hu
 * @data: 2019/7/9
 */
@Data
@TableName("t_user_sign_log")
public class UserSignLog extends Model<UserSignLog> implements Serializable {
    private static final long serialVersionUID = -3631138801678659294L;

    /**
     * 自增id
     */
    @TableId
    private Long id;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 签名
     */
    private String token;

    /**
     * 登录时间
     */
    private LocalDateTime signDate;

    /**
     * 过期时间(时间戳)
     */
    private LocalDateTime expirationTime;

    @Override
    protected Serializable pkVal() {
        return id;
    }
}
