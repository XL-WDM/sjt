package com.sjt.wechat.vo.res;

import lombok.Data;

/**
 * @author: yilan.hu
 * @data: 2019/7/10
 */
@Data
public class WxAppletSessionKeyVO extends WxResultVO {
    /**
     * 用户唯一标识
     */
    private String openid;

    /**
     * 会话密钥
     */
    private String session_key;

    /**
     * 用户在开放平台的唯一标识符
     */
    private String unionid;
}
