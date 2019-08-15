package com.sjt.business.service.bo.sf;

import lombok.Data;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

/**
 * @author: yilan.hu
 * @data: 2019/8/15
 */
@Data
@Root(name = "RouteResponse", strict = false)
public class SfRouteResponse {

    /**
     * 运单号
     */
    @Attribute(name = "mailno")
    private String mailNo;

    /**
     * 路由
     */
    @ElementList(inline = true, required = false)
    private List<SfRoute> routes;
}
