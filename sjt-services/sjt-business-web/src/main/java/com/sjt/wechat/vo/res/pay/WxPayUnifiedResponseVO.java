package com.sjt.wechat.vo.res.pay;

import lombok.Data;
import org.simpleframework.xml.Element;

/**
 * @author: yilan.hu
 * @data: 2019/8/1
 */
@Data
public class WxPayUnifiedResponseVO extends WxPayResultVO {

    /**
     * 预支付交易会话标识
     */
    @Element(
        name = "prepay_id",
        required = false
    )
    private String prepayId;

    /**
     * 二维码链接
     */
    @Element(
        name = "code_url",
        required = false
    )
    private String codeUrl;
}
