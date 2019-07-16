package com.sjt.wechat.api.dto.res;

import lombok.Data;

/**
 * @author: yilan.hu
 * @data: 2019/7/16
 */
@Data
public class WxAppletSessionKeyDTO {
    /**
     * 用户唯一标识
     */
    private String openid;

    /**
     * 会话密钥
     */
    private String sessionKey;

    /**
     * 用户在开放平台的唯一标识符
     */
    private String unionid;
}
