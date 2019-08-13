package com.sjt.business.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.sjt.business.entity.Order;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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

    /**
     * 查询过期订单
     * @return
     */
    List<Order> selectOverdueOrder();

    /**
     * 查询待收货超过7天的订单
     * @return
     */
    List<Order> selectAutoCompleteOrder();
}
