package com.sjt.business.api.impl;

import com.sjt.business.api.expose.LogisticsManageApi;
import com.sjt.business.service.IShunFengService;
import com.sjt.common.base.result.ResultDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: yilan.hu
 * @data: 2019/8/9
 */
@RestController
public class LogisticsManageApiService implements LogisticsManageApi {

    @Autowired
    private IShunFengService iShunFengService;

    @Override
    public ResultDTO queryLogistics(String orderId) {
        iShunFengService.routeQuery();

        return ResultDTO.success();
    }
}
