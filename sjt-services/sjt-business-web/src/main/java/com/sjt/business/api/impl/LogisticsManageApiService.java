package com.sjt.business.api.impl;

import com.sjt.business.api.dto.res.RouteInfoDTO;
import com.sjt.business.api.dto.res.SfRouteDTO;
import com.sjt.business.api.expose.LogisticsManageApi;
import com.sjt.business.service.IShunFengService;
import com.sjt.common.base.result.ResultDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author: yilan.hu
 * @data: 2019/8/9
 */
@RestController
public class LogisticsManageApiService implements LogisticsManageApi {

    @Autowired
    private IShunFengService iShunFengService;

    @Override
    public ResultDTO<RouteInfoDTO> queryLogistics(String orderNo) {
        RouteInfoDTO routeInfoDTO = iShunFengService.platformRouteQuery(orderNo);

        return ResultDTO.data(routeInfoDTO);
    }

    @Override
    public ResultDTO<List<SfRouteDTO>> manageQueryLogistics(String orderNo) {
        List<SfRouteDTO> sfRouteDTOS = iShunFengService.manageRouteQuery(orderNo);

        return ResultDTO.data(sfRouteDTOS);
    }
}
