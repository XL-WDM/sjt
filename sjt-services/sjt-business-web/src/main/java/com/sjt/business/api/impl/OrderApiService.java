package com.sjt.business.api.impl;

import com.sjt.business.api.dto.req.OrderEditParamDTO;
import com.sjt.business.api.dto.req.OrderManageParamDTO;
import com.sjt.business.api.dto.req.OrderParamDTO;
import com.sjt.business.api.dto.req.PlaceOrderParamDTO;
import com.sjt.business.api.dto.res.OrderDTO;
import com.sjt.business.api.dto.res.OrderManageInfoDTO;
import com.sjt.business.api.dto.res.PlaceOrderDTO;
import com.sjt.business.api.expose.OrderApi;
import com.sjt.business.service.IOrderService;
import com.sjt.common.base.result.ResultDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
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
    public ResultDTO<PlaceOrderDTO> placeOrder(@RequestBody PlaceOrderParamDTO placeOrderParamDTO) {
        PlaceOrderDTO placeOrderDTO = iOrderService.placeOrder(placeOrderParamDTO);
        return ResultDTO.data(placeOrderDTO);
    }

    @Override
    public ResultDTO<List<OrderDTO>> getOrder(OrderParamDTO orderParamDTO) {
        List<OrderDTO> rows = iOrderService.getOrderListByPage(orderParamDTO);
        Integer total = iOrderService.getOrderCountByPage(orderParamDTO);
        return ResultDTO.page(total, rows);
    }

    @Override
    public ResultDTO<OrderDTO> getOrderDetail(Long orderId) {
        OrderDTO orderDetail = iOrderService.getOrderDetail(orderId);
        return ResultDTO.data(orderDetail);
    }

    @Override
    public ResultDTO<List<OrderManageInfoDTO>> getOrders(OrderManageParamDTO orderManageParamDTO) {
        List<OrderManageInfoDTO> rows = iOrderService.getOrderManageListByPage(orderManageParamDTO);
        Integer total = iOrderService.getOrderManageCountByPage(orderManageParamDTO);

        return ResultDTO.page(total, rows);
    }

    @Override
    public ResultDTO editOrder(@RequestBody OrderEditParamDTO orderEditParamDTO) {
        iOrderService.editOrder(orderEditParamDTO);

        return ResultDTO.success();
    }

    @Override
    public ResultDTO<OrderDTO> getOrderManageDetail(Long orderId) {
        OrderDTO orderDTO = iOrderService.getOrderManageDetail(orderId);

        return ResultDTO.data(orderDTO);
    }
}
