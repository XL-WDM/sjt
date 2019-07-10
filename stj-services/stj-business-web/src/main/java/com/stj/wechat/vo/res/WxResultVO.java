package com.stj.wechat.vo.res;

import lombok.Data;

/**
 * @author: yilan.hu
 * @data: 2019/7/9
 */
@Data
public class WxResultVO {

    /**
     * 成功返回
     */
    public static final Integer WX_SUCCESS = 0;

    /**
     * 返回码
     * -1	    系统繁忙，此时请开发者稍候再试
     * 0	    请求成功
     * 40001	AppSecret错误或者AppSecret不属于这个公众号，请开发者确认AppSecret的正确性
     * 40002	请确保grant_type字段值为client_credential
     * 40164	调用接口的IP地址不在白名单中，请在接口IP白名单中进行设置。（小程序及小游戏调用不要求IP地址在白名单内。）
     */
    private Integer errcode;

    /**
     * 返回信息
     */
    private String errmsg;

    /**
     * msgid
     */
    private String msgid;

    public final boolean isSuccess() {
        return errcode == null || WX_SUCCESS.equals(errcode);
    }
}
