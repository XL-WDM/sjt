package com.sjt.business.service.bo.sf;

import lombok.Data;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.Text;

import java.io.Serializable;

/**
 * @author: yilan.hu
 * @data: 2019/8/15
 */
@Data
@Root(name = "Response", strict = false)
public class SfResponse implements Serializable {
    private static final long serialVersionUID = 1859642131889331694L;

    private static final String SUCCESS_CODE = "OK";

    /**
     * 服务名
     */
    @Attribute(name = "service")
    private String service;

    /**
     * 响应状态
     */
    @Element(name = "Head")
    private String head;

    /**
     * 响应失败原因
     */
    @Element(name = "ERROR", required = false)
    private ERROR error;

    /**
     * 响应结果
     */
    @Element(name = "Body", required = false)
    private SfResponseBody Body;

    @Data
    @Root(name = "ERROR", strict = false)
    public class ERROR {
        @Attribute(name = "code")
        private String code;

        @Text
        private String text;
    }

    public boolean isSuccess() {
        return error == null && SUCCESS_CODE.equals(head);
    }
}
