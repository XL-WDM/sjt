package com.sjt.business.service.bo.sf;

import lombok.Data;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * @author: yilan.hu
 * @data: 2019/8/15
 */
@Data
@Root(name = "Body", strict = false)
public class SfResponseBody {

    @Element(name = "RouteResponse", required = false)
    private SfRouteResponse routeResponse;
}
