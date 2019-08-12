package com.sjt.business.service;

import com.sjt.business.api.dto.req.OrderManageParamDTO;
import com.sjt.business.api.dto.req.OrderParamDTO;
import com.sjt.business.api.dto.req.PlaceOrderParamDTO;
import com.sjt.business.api.dto.res.OrderDTO;
import com.sjt.business.api.dto.res.OrderManageInfoDTO;
import com.sjt.business.api.dto.res.PlaceOrderDTO;

import java.util.List;

/**
 * @author: yilan.hu
 * @data: 2019/7/18
 */
public interface IOrderService {

    /**
     * 下单
     * @param placeOrderParamDTO
     * @return
     */
    PlaceOrderDTO placeOrder(PlaceOrderParamDTO placeOrderParamDTO);

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

    /**
     * 获取订单管理分页总数
     * @param orderManageParamDTO
     * @return
     */
    Integer getOrderManageCountByPage(OrderManageParamDTO orderManageParamDTO);

    /**
     * 分页获取订单管理信息
     * @param orderManageParamDTO
     * @return
     */
    List<OrderManageInfoDTO> getOrderManageListByPage(OrderManageParamDTO orderManageParamDTO);
}
