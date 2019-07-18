package com.sjt.business.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.sjt.business.api.dto.req.OrderParamDTO;
import com.sjt.business.entity.OrderItem;
import com.sjt.business.entity.Product;
import com.sjt.business.entity.ProductStock;
import com.sjt.business.mapper.ProductMapper;
import com.sjt.business.mapper.ProductStockMapper;
import com.sjt.business.service.IOrderService;
import com.sjt.common.utils.CheckObjects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: yilan.hu
 * @data: 2019/7/18
 */
@Service
public class OrderServiceImpl implements IOrderService {

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private ProductStockMapper productStockMapper;

    @Override
    public void placeOrder(List<OrderParamDTO> orderItems) {
        // 1.参数校验
        CheckObjects.isEmpty(orderItems, "请选择需要下单的商品");

        // 下单错误提示
        List<String> msgs = new ArrayList<>();

        // 2.查询商品
        List<OrderItem> items = new ArrayList<>();
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
            CheckObjects.isNull(productStock, "商品[" + product.getProductName() + "], 库存不足");
            int stockNum = productStock.getProductStockNum() - productStock.getOrderStockNum();
            CheckObjects.predicate(stockNum, n -> n <= 0, "商品[" +product.getProductName() + "], 库存不足");
            // 2-4.下单数量
            CheckObjects.isNull(num, "请填写商品[" + product.getProductName() + "], 的购买数量");
            CheckObjects.predicate(num, n -> n < 1, "商品[" + product.getProductName() + "], 至少购买一件");
            CheckObjects.predicate(num, n -> n > stockNum,
                    "商品[" + product.getProductName() + "], 库存不足, 当前库存: " + stockNum + "件");

        }


    }
}
