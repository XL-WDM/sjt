package com.sjt.wechat.vo.res.pay;

import lombok.Data;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * 微信小程序返回结果
 * @author: yilan.hu
 * @data: 2019/7/31
 */
@Root(
    name = "xml",
    strict = false
)
@Data
public class WxPayResultVO {

    private static final String FAIL = "FAIL";
    private static final String SUCCESS = "SUCCESS";

    /**
     * 返回状态码
     */
    @Element(
        name = "return_code"
    )
    private String returnCode;

    /**
     * 返回信息
     */
    @Element(
        name = "return_msg"
    )
    private String returnMsg;

    /**
     * 业务结果
     */
    @Element(
        name = "result_code",
        required = false
    )
    private String resultCode;

    /**
     * 错误代码
     */
    @Element(
        name = "err_code",
        required = false
    )
    private String errCode;

    /**
     * 错误代码描述
     */
    @Element(
        name = "err_code_des",
        required = false
    )
    private String errCodeDes;

    public boolean isFail() {
        return FAIL.equals(returnCode);
    }

    public boolean isSuccess() {
        return SUCCESS.equals(returnCode) && SUCCESS.equals(resultCode);
    }
}
