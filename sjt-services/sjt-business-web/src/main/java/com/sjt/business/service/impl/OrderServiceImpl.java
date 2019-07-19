package com.sjt.business.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.sjt.business.api.dto.req.OrderItemParamDTO;
import com.sjt.business.api.dto.req.OrderParamDTO;
import com.sjt.business.api.dto.req.PlaceOrderParamDTO;
import com.sjt.business.api.dto.res.OrderDTO;
import com.sjt.business.api.dto.res.OrderItemDTO;
import com.sjt.business.constant.DataBaseConstant;
import com.sjt.business.entity.*;
import com.sjt.business.mapper.*;
import com.sjt.business.service.IOrderService;
import com.sjt.business.web.config.WebUserContext;
import com.sjt.common.base.constant.ResultConstant;
import com.sjt.common.utils.BeanCopierUtils;
import com.sjt.common.utils.CheckObjects;
import com.sjt.common.utils.PriceUtils;
import com.sjt.common.utils.SnowflakeIdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

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
    private AddressMapper addressMapper;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private ProductStockMapper productStockMapper;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderItemMappler orderItemMappler;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void placeOrder(PlaceOrderParamDTO placeOrderParamDTO) {
        // 1.参数校验
        CheckObjects.isNull(placeOrderParamDTO, ResultConstant.PARAMETERS_CANNOT_BE_NULL);
        List<OrderItemParamDTO> orderItems = placeOrderParamDTO.getOrderItems();
        Long receivingId = placeOrderParamDTO.getReceivingId();
        CheckObjects.isEmpty(orderItems, "请选择需要下单的商品");
        CheckObjects.isNull(receivingId, "请选择收货地址");
        Long userId = WebUserContext.getContext().getId();
        Address address = new Address();
        address.setId(receivingId);
        address.setUserId(userId);
        address = addressMapper.selectAddressByIdAndUserId(address);
        CheckObjects.isNull(address, "收货地址不存在");

        // 商品总金额
        BigDecimal sumTotalAmount = new BigDecimal("0");
        // 订单总优惠金额
        BigDecimal discountSumAmount = new BigDecimal("0");
        // 订单详情集合
        List<OrderItem> orderDetails = new ArrayList<>();

        // 2.查询商品
        for (OrderItemParamDTO orderItem : orderItems) {
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

            // 2-6.创建订单详情对象
            OrderItem oItem = new OrderItem();
            oItem.setProductId(product.getId());
            oItem.setNum(num);
            oItem.setPrice(product.getPrice());
            // 2-7.订单详情商品总金额
            BigDecimal itemSumTotalAmount = product.getPrice().multiply(BigDecimal.valueOf(num));
            oItem.setTotalFee(itemSumTotalAmount);
            // 2-8.订单详情优惠总金额
            BigDecimal discountAmount = product.getDiscountAmount().multiply(BigDecimal.valueOf(num));
            oItem.setDiscountAmount(discountAmount);
            oItem.setProductName(product.getProductName());
            oItem.setProductDescript(product.getDescript());
            oItem.setProductImg("");

            // 2-9.添加到集合
            orderDetails.add(oItem);

            // 2-10.累计订单总金额
            sumTotalAmount = sumTotalAmount.add(itemSumTotalAmount);
            discountSumAmount = discountSumAmount.add(discountAmount);
        }

        // 运费
        BigDecimal postFee = new BigDecimal("1500");

        // 3.生成订单信息
        Order order = new Order();
        order.setOrderNo(String.valueOf(snowflakeIdUtils.nextId()));
        order.setUserId(userId);
        order.setAddressId(address.getId());
        order.setTotalAmount(sumTotalAmount);
        // 订单总金额(商品总金额 + 运费 - (优惠金额、红包抵扣、积分抵扣))
        order.setOrgAmount(sumTotalAmount.add(postFee).subtract(discountSumAmount));
        order.setDiscountAmount(discountSumAmount);
        order.setPostFee(postFee);
        order.setStatus(DataBaseConstant.OrderStatus.TO_BE_PAID.getCode());
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

    @Override
    public Integer getOrderCountByPage(OrderParamDTO orderParamDTO) {
        // 1.参数校验
        CheckObjects.isNull(orderParamDTO, ResultConstant.PARAMETERS_CANNOT_BE_NULL);
        String status = orderParamDTO.getStatus();
        if (!StringUtils.isEmpty(status)) {
            DataBaseConstant.OrderStatus orderStatusEnum = DataBaseConstant.OrderStatus.find(status);
            status = orderStatusEnum == null ? null : orderStatusEnum.getCode();
        }

        // 2.查询总数
        return  orderMapper.selectCount(new EntityWrapper<Order>()
                .eq("user_id", WebUserContext.getContext().getId())
                .eq(!StringUtils.isEmpty(status), "status", status));
    }

    @Override
    public List<OrderDTO> getOrderListByPage(OrderParamDTO orderParamDTO) {
        // 1.参数校验
        CheckObjects.isNull(orderParamDTO, ResultConstant.PARAMETERS_CANNOT_BE_NULL);
        Integer pageNo = orderParamDTO.getPageNo();
        Integer pageSize = orderParamDTO.getPageSize();
        CheckObjects.isPage(pageNo, pageSize);
        String status = orderParamDTO.getStatus();
        if (!StringUtils.isEmpty(status)) {
            DataBaseConstant.OrderStatus orderStatusEnum = DataBaseConstant.OrderStatus.find(status);
            status = orderStatusEnum == null ? null : orderStatusEnum.getCode();
        }

        // 2.查询订单
        List<Order> orders = orderMapper.selectPage(new Page<Order>(pageNo, pageSize),
                new EntityWrapper<Order>()
                        .eq("user_id", WebUserContext.getContext().getId())
                        .eq(!StringUtils.isEmpty(status), "status", status)
                        .orderBy("create_date", false));

        // 3.查询订单详情
        List<OrderDTO> orderDTOS = new ArrayList<>();
        for (Order order : orders) {
            // 3-1.Entity -> DTO
            OrderDTO orderDTO = BeanCopierUtils.copyBean(order, OrderDTO.class);
            // 3-2.分 -> 元
            orderDTO.setTotalAmount(PriceUtils.centToYuan(orderDTO.getTotalAmount()));
            orderDTO.setOrgAmount(PriceUtils.centToYuan(orderDTO.getOrgAmount()));
            orderDTO.setPostFee(PriceUtils.centToYuan(orderDTO.getPostFee()));


            // 3-3.获取订单详情
            List<OrderItem> orderItems = orderItemMappler.selectList(new EntityWrapper<OrderItem>()
                    .eq("order_id", order.getId()));

            // 3-4.设置订单详情信息
            if (orderItems != null && !orderItems.isEmpty()) {
                List<OrderItemDTO> orderItemDTOS = new ArrayList<>();
                for (OrderItem orderItem : orderItems) {
                    OrderItemDTO orderItemDTO = BeanCopierUtils.copyBean(orderItem, OrderItemDTO.class);
                    // 分 -> 元
                    orderItemDTO.setPrice(PriceUtils.centToYuan(orderItemDTO.getPrice()));
                    orderItemDTOS.add(orderItemDTO);
                }
                orderDTO.setOrderItems(orderItemDTOS);
            }

            // 3-5.添加详情到集合
            orderDTOS.add(orderDTO);
        }

        return orderDTOS;
    }

    @Override
    public OrderDTO getOrderDetail(Long orderId) {
        // 1.参数校验
        CheckObjects.isNull(orderId, "请选择需要查询的订单");
        Order order = new Order();
        order.setId(orderId);
        order.setUserId(WebUserContext.getContext().getId());
        order = orderMapper.selectOne(order);
        CheckObjects.isNull(order, "订单不存在");

        // 3-1.Entity -> DTO
        OrderDTO orderDTO = BeanCopierUtils.copyBean(order, OrderDTO.class);
        // 3-2.分 -> 元
        orderDTO.setTotalAmount(PriceUtils.centToYuan(orderDTO.getTotalAmount()));
        orderDTO.setOrgAmount(PriceUtils.centToYuan(orderDTO.getOrgAmount()));
        orderDTO.setPostFee(PriceUtils.centToYuan(orderDTO.getPostFee()));


        // 3-3.获取订单详情
        List<OrderItem> orderItems = orderItemMappler.selectList(new EntityWrapper<OrderItem>()
                .eq("order_id", order.getId()));

        // 3-4.设置订单详情信息
        if (orderItems != null && !orderItems.isEmpty()) {
            List<OrderItemDTO> orderItemDTOS = new ArrayList<>();
            for (OrderItem orderItem : orderItems) {
                OrderItemDTO orderItemDTO = BeanCopierUtils.copyBean(orderItem, OrderItemDTO.class);
                // 分 -> 元
                orderItemDTO.setPrice(PriceUtils.centToYuan(orderItemDTO.getPrice()));
                orderItemDTOS.add(orderItemDTO);
            }
            orderDTO.setOrderItems(orderItemDTOS);
        }

        return orderDTO;
    }
}
