package com.stj.common.base.constant;

/**
 * @author: yilan.hu
 * @data: 2019/6/20
 */
public class WechatConstant {
    /**
     * 用户同意授权, 获取 code, scope为snsapi_base
     */
    public final static String OAUTH_CODE_SNSAPI_BASE_GET_URL = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect";

    /**
     * 用户同意授权, 获取 code, scope为snsapi_userinfo
     */
    public final static String OAUTH_CODE_SNSAPI_USERINFO_GET_URL = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect";

    /**
     * 获取access_token
     */
    public final static String ACCESS_TOKEN_GET_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";


    /**
     * 发送模板消息
     */
    public final static String TEMPLDATE_MESSAGE_POST_URL = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=ACCESS_TOKEN";

    /**
     *
     */
    public final static String GET_TEMPLATE_LIST_GET_URL = "https://api.weixin.qq.com/cgi-bin/template/get_all_private_template?access_token=ACCESS_TOKEN";
}
