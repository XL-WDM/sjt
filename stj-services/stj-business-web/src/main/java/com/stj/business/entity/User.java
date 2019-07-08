package com.stj.business.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author: yilan.hu
 * @data: 2019/7/8
 */
public class User extends Model<User> implements Serializable {

    private static final long serialVersionUID = -1810440280677864245L;

    /**
     * 自增ID
     */
    @TableId
    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 密码
     */
    private String password;

    /**
     * 头像
     */
    private String faceUrl;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 性别
     */
    private String sex;

    /**
     * 生日
     */
    private String birtrday;

    /**
     * 状态(0-冻结 1-可用)
     */
    private String status;

    /**
     * 注册时间
     */
    private LocalDateTime registerDate;

    /**
     * 更新时间
     */
    private LocalDateTime updateDate;

    /**
     * 版本号
     */
    private Integer version;

    @Override
    protected Serializable pkVal() {
        return id;
    }
}
