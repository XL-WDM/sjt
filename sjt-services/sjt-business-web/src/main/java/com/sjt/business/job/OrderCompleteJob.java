package com.sjt.business.job;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.sjt.business.constant.DataBaseConstant;
import com.sjt.business.entity.Order;
import com.sjt.business.mapper.OrderMapper;
import com.sjt.common.base.constant.BaseConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 订单待收货超过7天自动完成订单
 * @author: yilan.hu
 * @data: 2019/8/13
 */
@Slf4j
@Component
public class OrderCompleteJob {

    @Autowired
    private OrderMapper orderMapper;

    @Scheduled(fixedRate = BaseConstant.Time.MINUTE)
    private void job() {
        // 查询待收货超过7天的订单
        List<Order> orders = orderMapper.selectAutoCompleteOrder();
        for (Order o : orders) {
            try {
                // 处理已完成
                Order order = new Order();
                order.setStatus(DataBaseConstant.OrderStatus.COMPLETED.getCode());
                order.setEndDate(LocalDateTime.now());
                order.setUpdateDate(LocalDateTime.now());
                boolean rows = order.update(new EntityWrapper<Order>().eq("id", o.getId()));
                if (!rows) {
                    log.error("## 【自动完成订单job】 -> 处理失败 Update result rows = false, orderNo: {}", o.getOrderNo());
                }
            } catch (Exception e) {
                log.error("## 【自动完成订单job】 -> 处理失败 orderNo: {}", o.getOrderNo(), e);
            }
        }
    }
}
