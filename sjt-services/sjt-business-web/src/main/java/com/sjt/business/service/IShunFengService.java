package com.sjt.business.service;

import com.sjt.business.api.dto.res.RouteInfoDTO;
import com.sjt.business.api.dto.res.SfRouteDTO;

import java.util.List;

/**
 * @author: yilan.hu
 * @data: 2019/8/9
 */
public interface IShunFengService {

    /**
     * 路由查询-通过运单号(电商平台-manageRouteQuery基础上加校验)
     * @param orderNo
     * @return
     */
    RouteInfoDTO platformRouteQuery(String orderNo);

    /**
     * 路由查询-通过运单号)
     * @param orderNo
     * @return
     */
    List<SfRouteDTO> manageRouteQuery(String orderNo);
}
