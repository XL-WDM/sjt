package com.sjt.wechat.api.dto.res;

import lombok.Data;

/**
 * @author: yilan.hu
 * @data: 2019/7/16
 */
@Data
public class WxSnsapiUserInfoDTO {

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
}
