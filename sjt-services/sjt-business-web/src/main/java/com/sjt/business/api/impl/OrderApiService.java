package com.sjt.business.api.impl;

import com.sjt.business.api.dto.req.OrderParamDTO;
import com.sjt.business.api.dto.req.PlaceOrderParamDTO;
import com.sjt.business.api.dto.res.OrderDTO;
import com.sjt.business.api.expose.OrderApi;
import com.sjt.business.service.IOrderService;
import com.sjt.common.base.result.ResultDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author: yilan.hu
 * @data: 2019/7/18
 */
@RestController
public class OrderApiService implements OrderApi {

    @Autowired
    private IOrderService iOrderService;

    @Override
    public ResultDTO placeOrder(PlaceOrderParamDTO placeOrderParamDTO) {
        iOrderService.placeOrder(placeOrderParamDTO);
        return ResultDTO.success();
    }

    @Override
    public ResultDTO<List<OrderDTO>> getOrder(OrderParamDTO orderParamDTO) {
        List<OrderDTO> rows = iOrderService.getOrderListByPage(orderParamDTO);
        Integer total = iOrderService.getOrderCountByPage(orderParamDTO);
        return ResultDTO.page(total, rows);
    }
}
