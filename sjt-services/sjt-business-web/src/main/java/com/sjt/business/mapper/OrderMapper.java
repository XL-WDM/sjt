package com.sjt.business.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.sjt.business.entity.Order;

/**
 * @author: yilan.hu
 * @data: 2019/7/18
 */
public interface OrderMapper extends BaseMapper<Order> {

    /**
     * 通过订单编号查询订单
     * @param orderNo
     * @return
     */
    Order selectOneByOrderNo(String orderNo);
}
