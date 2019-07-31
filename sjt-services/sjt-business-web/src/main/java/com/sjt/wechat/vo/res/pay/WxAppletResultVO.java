package com.sjt.wechat.vo.res.pay;

import lombok.Data;

/**
 * 微信小程序返回结果
 * @author: yilan.hu
 * @data: 2019/7/31
 */
@Data
public class WxAppletResultVO {

    /**
     * 返回状态码
     */
    private String return_code;

    /**
     * 返回信息
     */
    private String return_msg;

    private String result_code;

    private String err_code;
}
