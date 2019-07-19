package com.sjt.business.api.impl;

import com.sjt.business.api.dto.req.OrderParamDTO;
import com.sjt.business.api.expose.OrderApi;
import com.sjt.business.service.IOrderService;
import com.sjt.common.base.result.ResultDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: yilan.hu
 * @data: 2019/7/18
 */
@RestController
public class OrderApiService implements OrderApi {

    @Autowired
    private IOrderService iOrderService;

    @Override
    public ResultDTO placeOrder(@RequestBody OrderParamDTO orderParamDTO) {

        iOrderService.placeOrder(orderParamDTO);
        return ResultDTO.success();
    }
}
