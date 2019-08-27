package com.sjt.business.job;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.sjt.business.constant.DataBaseConstant;
import com.sjt.business.entity.Order;
import com.sjt.business.entity.OrderItem;
import com.sjt.business.entity.ProductSpec;
import com.sjt.business.mapper.OrderItemMappler;
import com.sjt.business.mapper.OrderMapper;
import com.sjt.business.mapper.ProductSpecMapper;
import com.sjt.business.service.IOrderService;
import com.sjt.common.base.constant.BaseConstant;
import com.sjt.common.exceptions.GlobalException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 订单未支付超过1天自动失效
 * @author: yilan.hu
 * @data: 2019/8/6
 */
@Slf4j
@Component
public class OrderOverdueJob {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private IOrderService iOrderService;

    @Scheduled(fixedRate = BaseConstant.Time.MINUTE)
    private void job() {
        // 1.查询过期订单
        List<Order> orders = orderMapper.selectOverdueOrder();
        for (Order order : orders) {
            try {
                // 2.失效订单
                iOrderService.fallbackOrder(order.getId());

                log.info("【订单过期处理job】 订单: {}, 已处理", order.getOrderNo());
            } catch (Exception e) {
                log.error("## 【过期订单job】 -> 处理失败 orderNo: {}", order.getOrderNo(), e);
            }
        }
    }
}
