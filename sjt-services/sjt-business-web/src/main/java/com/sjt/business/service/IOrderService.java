package com.sjt.business.service;

import com.sjt.business.api.dto.req.OrderParamDTO;

/**
 * @author: yilan.hu
 * @data: 2019/7/18
 */
public interface IOrderService {

    /**
     * 下单
     * @param orderParamDTO
     */
    void placeOrder(OrderParamDTO orderParamDTO);
}
