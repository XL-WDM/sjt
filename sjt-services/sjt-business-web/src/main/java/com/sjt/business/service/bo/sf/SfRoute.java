package com.sjt.business.service.bo.sf;

import lombok.Data;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

/**
 * @author: yilan.hu
 * @data: 2019/8/15
 */
@Data
@Root(name = "Route", strict = false)
public class SfRoute {

    /**
     * 路由节点具体描述
     */
    @Attribute(name = "remark")
    private String remark;

    /**
     * 路由节点发生的时间
     */
    @Attribute(name = "accept_time")
    private String acceptTime;

    /**
     * 路由节点发生的地点
     */
    @Attribute(name = "accept_address")
    private String acceptAddress;

    /**
     * 路由节点操作码
     */
    @Attribute(name = "opcode")
    private String opcode;

}
