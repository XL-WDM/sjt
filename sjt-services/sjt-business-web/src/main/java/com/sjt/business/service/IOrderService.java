package com.sjt.business.service;

import com.sjt.business.api.dto.req.OrderParamDTO;

import java.util.List;

/**
 * @author: yilan.hu
 * @data: 2019/7/18
 */
public interface IOrderService {

    /**
     * 下单
     * @param orderItems
     * @param receivingId
     */
    void placeOrder(List<OrderParamDTO> orderItems, Long receivingId);
}
