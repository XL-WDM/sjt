package com.sjt.wechat.constant;

/**
 * @author: yilan.hu
 * @data: 2019/7/9
 */
public class WechatConstant {

    // ===================== 微信公众号 =====================
    /**
     * 用户同意授权, 获取 code, scope为snsapi_base
     */
    public static final String OAUTH_CODE_SNSAPI_BASE_GET_URL = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect";

    /**
     * 用户同意授权, 获取 code, scope为snsapi_userinfo
     */
    public static final String OAUTH_CODE_SNSAPI_USERINFO_GET_URL = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect";

    /**
     * 获取access_token
     */
    public static final String ACCESS_TOKEN_GET_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";

    /**
     * 通过code换取网页授权access_token
     */
    public static final String OAUTH_ACCESS_TOKEN_GET_URL = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";

    /**
     * 刷新access_token
     */
    public static final String REFRESH_TOKEN_GET_URL = "https://api.weixin.qq.com/sns/oauth2/refresh_token?appid=APPID&grant_type=refresh_token&refresh_token=REFRESH_TOKEN";

    /**
     * 拉取用户信息(需scope为 snsapi_userinfo)
     */
    public static final String PULL_SNSAPI_USERINFO_GET_URL = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";

    /**
     * 发送模板消息
     */
    public static final String TEMPLDATE_MESSAGE_POST_URL = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=ACCESS_TOKEN";

    /**
     * 获取模板列表
     */
    public static final String GET_TEMPLATE_LIST_GET_URL = "https://api.weixin.qq.com/cgi-bin/template/get_all_private_template?access_token=ACCESS_TOKEN";


    // ===================== 微信小程序 =====================
    /**
     * 登录凭证校验 auth.code2Session
     */
    public static final String APPLET_CODE_2_SESSION_GET_URL = "https://api.weixin.qq.com/sns/jscode2session?appid=APPID&secret=SECRET&js_code=JSCODE&grant_type=authorization_code";

    /**
     * 统一下单
     */
    public static final String UNIFIED_ORDER = "https://api.mch.weixin.qq.com/pay/unifiedorder";
}
