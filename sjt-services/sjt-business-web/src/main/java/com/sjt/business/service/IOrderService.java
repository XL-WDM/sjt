package com.sjt.business.service;

import com.sjt.business.api.dto.req.OrderParamDTO;
import com.sjt.business.api.dto.req.PlaceOrderParamDTO;
import com.sjt.business.api.dto.res.OrderDTO;

import java.util.List;

/**
 * @author: yilan.hu
 * @data: 2019/7/18
 */
public interface IOrderService {

    /**
     * 下单
     * @param placeOrderParamDTO
     */
    void placeOrder(PlaceOrderParamDTO placeOrderParamDTO);

    /**
     * 获取订单总数
     * @param orderParamDTO
     * @return
     */
    Integer getOrderCountByPage(OrderParamDTO orderParamDTO);

    /**
     * 分页获取订单信息
     * @param orderParamDTO
     * @return
     */
    List<OrderDTO> getOrderListByPage(OrderParamDTO orderParamDTO);

    /**
     * 获取订单详情
     * @param orderId
     * @return
     */
    OrderDTO getOrderDetail(Long orderId);
}
