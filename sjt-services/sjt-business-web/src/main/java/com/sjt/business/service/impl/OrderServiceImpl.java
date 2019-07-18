package com.sjt.business.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.sjt.business.api.dto.req.OrderParamDTO;
import com.sjt.business.constant.DataBaseConstant;
import com.sjt.business.entity.Order;
import com.sjt.business.entity.OrderItem;
import com.sjt.business.entity.Product;
import com.sjt.business.entity.ProductStock;
import com.sjt.business.mapper.ProductMapper;
import com.sjt.business.mapper.ProductStockMapper;
import com.sjt.business.service.IOrderService;
import com.sjt.business.web.config.WebUserContext;
import com.sjt.common.exceptions.GlobalException;
import com.sjt.common.utils.CheckObjects;
import com.sjt.common.utils.SnowflakeIdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: yilan.hu
 * @data: 2019/7/18
 */
@Service
public class OrderServiceImpl implements IOrderService {

    @Autowired
    private SnowflakeIdUtils snowflakeIdUtils;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private ProductStockMapper productStockMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void placeOrder(List<OrderParamDTO> orderItems) {
        // 1.参数校验
        CheckObjects.isEmpty(orderItems, "请选择需要下单的商品");

        // 订单总金额
        BigDecimal sumPrice = new BigDecimal("0");

        // 订单详情集合
        List<OrderItem> orderDetails = new ArrayList<>();

        // 2.查询商品
        for (OrderParamDTO orderItem : orderItems) {
            Long productId = orderItem.getProductId();
            Integer num = orderItem.getNum();
            CheckObjects.isNull(productId, "商品选择有误");
            // 2-1.查询商品
            Product product = productMapper.selectById(productId);
            CheckObjects.isNull(product, "商品不存在");
            // 2-2.商品库存查询
            ProductStock productStock = new ProductStock().selectOne(
                    new EntityWrapper<ProductStock>()
                            .eq("product_id", product.getId()));
            // 2-3.库存校验
            CheckObjects.isNull(productStock, "下单失败, 商品[" + product.getProductName() + "], 库存不足");
            int stockNum = productStock.getProductStockNum() - productStock.getOrderStockNum();
            CheckObjects.predicate(stockNum, n -> n <= 0, "下单失败, 商品[" +product.getProductName() + "], 库存不足");
            // 2-4.下单数量
            CheckObjects.isNull(num, "请填写商品[" + product.getProductName() + "], 的购买数量");
            CheckObjects.predicate(num, n -> n < 1, "下单失败, 商品[" + product.getProductName() + "], 至少购买一件");
            CheckObjects.predicate(num, n -> n > stockNum,
                    "下单失败, 商品[" + product.getProductName() + "], 库存不足, 当前库存: " + stockNum + "件");
            // 2-5.跟新库存
            int version = productStock.getVersion();
            productStock.setOrderStockNum(productStock.getOrderStockNum() + num);
            productStock.setVersion(version + 1);
            boolean rows = productStock.update(new EntityWrapper<ProductStock>()
                    .eq("id", productStock.getId())
                    .eq("version", version));
            CheckObjects.predicate(rows, r -> !r, "下单失败, 库存系统繁忙");

            // 3-6.创建订单详情对象
            OrderItem oItem = new OrderItem();
            oItem.setProductId(product.getId());
            oItem.setNum(num);
            oItem.setPrice(product.getPrice());
            // 订单详情总金额
            BigDecimal itemSumPrice = product.getPrice().multiply(BigDecimal.valueOf(num));
            oItem.setTotalFee(itemSumPrice);
            orderDetails.add(oItem);

            // 累计金额
            sumPrice = sumPrice.add(itemSumPrice);
        }

        // 3.生成订单信息
        Order order = new Order();
        order.setOrderNo(String.valueOf(snowflakeIdUtils.nextId()));
        order.setUserId(WebUserContext.getContext().getId());
        order.setOrgPayment(sumPrice);
        order.setPayment(sumPrice);
        order.setPostFee(new BigDecimal("15"));
        order.setStatus(DataBaseConstant.OrderStatus.UNPAID.getCode());
        boolean insert = order.insert();
        CheckObjects.predicate(insert, b -> !b, "订单生成失败");

        // 4.生成订单详情
        Long orderId = order.getId();
        for (OrderItem orderDetail : orderDetails) {
            orderDetail.setOrderId(orderId);
            boolean insertOrderDetail = orderDetail.insert();
            CheckObjects.predicate(insertOrderDetail, b -> !b, "订单详情生成失败");
        }
    }
}
