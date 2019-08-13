package com.sjt.business.job;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.sjt.business.constant.DataBaseConstant;
import com.sjt.business.entity.Order;
import com.sjt.business.entity.OrderItem;
import com.sjt.business.entity.ProductSpec;
import com.sjt.business.mapper.OrderItemMappler;
import com.sjt.business.mapper.OrderMapper;
import com.sjt.business.mapper.ProductSpecMapper;
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
    private OrderItemMappler orderItemMappler;

    @Autowired
    private ProductSpecMapper productSpecMapper;

    @Scheduled(fixedRate = BaseConstant.Time.MINUTE)
    private void job() {
        // 1.查询过期订单
        List<Order> orders = orderMapper.selectOverdueOrder();
        for (Order order : orders) {
            try {
                overdueOrder(order);
            } catch (Exception e) {
                log.error("## 【过期订单job】 -> 处理失败 orderNo: {}", order.getOrderNo(), e);
            }
        }
    }

    /**
     * 订单失效
     * @param order
     */
    @Transactional(rollbackFor = Exception.class)
    private void overdueOrder(Order o) {
        // 1.更新订单状态
        Order order = new Order();
        order.setStatus(DataBaseConstant.OrderStatus.CANCELLED.getCode());
        order.setUpdateDate(LocalDateTime.now());
        order.setCloseDate(LocalDateTime.now());
        order.updateById();

        // 2.查询订单详情
        List<OrderItem> orderItems = orderItemMappler.selectList(new EntityWrapper<OrderItem>()
                .eq("order_id", o.getId()));

        // 2.回退预减库存
        for (OrderItem orderItem : orderItems) {
            ProductSpec productSpec = productSpecMapper.selectById(orderItem.getProductSpecId());
            int version = productSpec.getVersion();

            // 更新库存
            ProductSpec ps = new ProductSpec();
            ps.setOrderStockNum(productSpec.getOrderStockNum() - orderItem.getNum());
            ps.setVersion(version + 1);
            boolean update = ps.update(new EntityWrapper()
                    .eq("id", productSpec.getId())
                    .eq("version", version));
            if (!update) {
                throw new GlobalException("并发更新预减库存失败");
            }
            log.info("【订单过期处理job】 订单: {}, 已处理", o.getOrderNo());
        }
    }
}
