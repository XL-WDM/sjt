package com.sjt.business.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 微信用户信息
 * @author: yilan.hu
 * @data: 2019/7/16
 */
@Data
@TableName("t_wx_snsapi_userinfo")
public class WxSnsapiUserInfo extends Model<WxSnsapiUserInfo> implements Serializable {
    private static final long serialVersionUID = -6303398787701000604L;

    /**
     * 自增id
     */
    @TableId
    private Long id;

    /**
     * 用户的唯一标识
     */
    private String openid;

    /**
     * 用户昵称
     */
    private String nickname;

    /**
     * 用户的性别，值为1时是男性，值为2时是女性，值为0时是未知
     */
    private String sex;

    /**
     * 用户个人资料填写的省份
     */
    private String province;

    /**
     * 普通用户个人资料填写的城市
     */
    private String city;

    /**
     * 国家，如中国为CN
     */
    private String country;

    /**
     * 用户头像
     */
    private String headimgurl;

    /**
     * unionid
     */
    private String unionid;

    @Override
    protected Serializable pkVal() {
        return id;
    }
}
