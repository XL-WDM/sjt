package com.sjt.business.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.sjt.business.api.dto.req.*;
import com.sjt.business.api.dto.res.OrderDTO;
import com.sjt.business.api.dto.res.OrderItemDTO;
import com.sjt.business.api.dto.res.OrderManageInfoDTO;
import com.sjt.business.api.dto.res.PlaceOrderDTO;
import com.sjt.business.constant.DataBaseConstant;
import com.sjt.business.entity.Order;
import com.sjt.business.entity.OrderItem;
import com.sjt.business.entity.Product;
import com.sjt.business.entity.ProductSpec;
import com.sjt.business.mapper.*;
import com.sjt.business.service.IOrderService;
import com.sjt.business.web.config.WebUserContext;
import com.sjt.common.base.constant.BaseConstant;
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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 订单逻辑处理
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
    private ProductSpecMapper productSpecMapper;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderItemMappler orderItemMappler;

    @Autowired
    private UserMapper userMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public PlaceOrderDTO placeOrder(PlaceOrderParamDTO placeOrderParamDTO) {
        // 1.参数校验
        CheckObjects.isNull(placeOrderParamDTO, ResultConstant.PARAMETERS_CANNOT_BE_NULL);
        List<OrderItemParamDTO> orderItems = placeOrderParamDTO.getOrderItems();
        CheckObjects.isEmpty(orderItems, "请选择需要下单的商品");

        // 商品总金额
        BigDecimal sumTotalAmount = new BigDecimal("0");
        // 订单总优惠金额
        BigDecimal discountSumAmount = new BigDecimal("0");
        // 订单详情集合
        List<OrderItem> orderDetails = new ArrayList<>();

        // 2.查询商品
        for (OrderItemParamDTO orderItem : orderItems) {
            Long specId = orderItem.getSpecId();
            Integer num = orderItem.getNum();
            CheckObjects.isNull(specId, "商品规格选择有误");
            // 2-1.查询商品规格
            ProductSpec productSpec = productSpecMapper.selectById(specId);
            CheckObjects.isNull(productSpec, "商品规格不存在");

            // 2-2.查询商品
            Product product = productMapper.selectById(productSpec.getProductId());
            CheckObjects.isNull(product, "商品不存在");
            CheckObjects.predicate(product.getPublishStatus(),
                    s -> DataBaseConstant.ProductPushStatus.LOWER_SHELF.getCode().equals(s),
                    "商品已下架");
            CheckObjects.predicate(product.getPublishStatus(),
                    s -> DataBaseConstant.ProductPushStatus.INVALID.getCode().equals(s),
                    "商品已被删除");
            // 2-3.商品库存
            Integer stockNum = productSpec.getStockNum();
            Integer orderStockNum = productSpec.getOrderStockNum();

            // 2-4.库存校验
            int stock = stockNum - orderStockNum;
            CheckObjects.predicate(stock, n -> n <= 0,
                    "下单失败, 商品[" + product.getProductName() + " - " + productSpec.getSpecName() + "], 库存不足");
            // 2-5.下单数量
            CheckObjects.isNull(num, "请填写商品[" + product.getProductName() + " - " + productSpec.getSpecName() + "], 的购买数量");
            CheckObjects.predicate(num, n -> n < 1,
                    "下单失败, 商品[" + product.getProductName() + " - " + productSpec.getSpecName() + "], 至少购买一件");
            CheckObjects.predicate(num, n -> n > stock,
                    "下单失败, 商品[" + product.getProductName() + " - " + productSpec.getSpecName() + "], 库存不足, 当前库存: " + stock + "件");
            // 2-6.预减库存
            int version = productSpec.getVersion();
            ProductSpec ps = new ProductSpec();
            ps.setOrderStockNum(orderStockNum + num);
            ps.setVersion(version + 1);
            boolean rows = ps.update(new EntityWrapper<ProductSpec>()
                    .eq("id", productSpec.getId())
                    .eq("version", version));
            CheckObjects.predicate(rows, r -> !r, "下单失败, 库存系统繁忙");

            // 2-7.创建订单详情对象
            OrderItem oItem = new OrderItem();
            oItem.setProductSpecId(productSpec.getId());
            oItem.setNum(num);
            oItem.setPrice(productSpec.getPrice());
            // 2-8.订单详情商品总金额
            BigDecimal itemSumTotalAmount = productSpec.getPrice().multiply(BigDecimal.valueOf(num));
            oItem.setTotalFee(itemSumTotalAmount);
            // 2-9.订单详情优惠总金额
            BigDecimal discountAmount = new BigDecimal("0");

            oItem.setDiscountAmount(new BigDecimal("0"));

            oItem.setProductName(productSpec.getSpecName());
            oItem.setProductDescript(product.getDescript());
            oItem.setProductImg(productSpec.getSpecImage());

            // 2-10.添加到集合
            orderDetails.add(oItem);

            // 2-11.累计订单总金额
            sumTotalAmount = sumTotalAmount.add(itemSumTotalAmount);
            discountSumAmount = discountSumAmount.add(discountAmount);
        }

        // 运费
        BigDecimal postFee = new BigDecimal("0");

        // 3.生成订单信息
        Order order = new Order();
        order.setOrderNo(String.valueOf(snowflakeIdUtils.getId()));
        Long userId = WebUserContext.getContext().getId();
        order.setUserId(userId);
        order.setTotalAmount(sumTotalAmount);
        // 订单总金额(商品总金额 + 运费 - (优惠金额、红包抵扣、积分抵扣))
        order.setOrgAmount(sumTotalAmount.add(postFee).subtract(discountSumAmount));
        order.setPayment(order.getOrgAmount());
        order.setDiscountAmount(discountSumAmount);
        order.setPostFee(postFee);
        order.setStatus(DataBaseConstant.OrderStatus.TO_BE_PAID.getCode());
        boolean insert = order.insert();
        CheckObjects.predicate(insert, b -> !b, "订单生成失败");

        // 4.生成订单详情
        Long orderId = order.getId();
        for (OrderItem item : orderDetails) {
            item.setOrderId(orderId);
            boolean insertOrderDetail = item.insert();
            CheckObjects.predicate(insertOrderDetail, b -> !b, "订单详情生成失败");
        }

        PlaceOrderDTO placeOrderDTO = new PlaceOrderDTO();
        placeOrderDTO.setId(order.getId());
        placeOrderDTO.setOrderNo(order.getOrderNo());

        return placeOrderDTO;
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

    @Override
    public Integer getOrderManageCountByPage(OrderManageParamDTO orderManageParamDTO) {
        // 1.参数校验
        CheckObjects.isNull(orderManageParamDTO, ResultConstant.PARAMETERS_CANNOT_BE_NULL);
        String status = orderManageParamDTO.getStatus();
        if (!StringUtils.isEmpty(status)) {
            DataBaseConstant.OrderStatus orderStatus = DataBaseConstant.OrderStatus.find(status);
            CheckObjects.isNull(orderStatus, "订单状态输入有误");
        }

        // 2.订单查询
        Wrapper<Order> entityWrapper = new EntityWrapper<Order>()
                .eq(!StringUtils.isEmpty(status), "status", status);

        String startDate = orderManageParamDTO.getStartDate();
        String endDate = orderManageParamDTO.getEndDate();
        if (!StringUtils.isEmpty(startDate)) {
            entityWrapper.andNew().ge("create_date",
                    LocalDateTime.parse(startDate + " " + BaseConstant.FormatDate.TIME_STRING_START,
                            DateTimeFormatter.ofPattern(BaseConstant.FormatDate.DATE_TIME)));
        }
        if (!StringUtils.isEmpty(endDate)) {
            entityWrapper.andNew().le("create_date",
                    LocalDateTime.parse(endDate + " " + BaseConstant.FormatDate.TIME_STRING_END,
                            DateTimeFormatter.ofPattern(BaseConstant.FormatDate.DATE_TIME)));
        }

        // 3.返回
        return orderMapper.selectCount(entityWrapper);
    }

    @Override
    public List<OrderManageInfoDTO> getOrderManageListByPage(OrderManageParamDTO orderManageParamDTO) {
        // 1.参数校验
        CheckObjects.isNull(orderManageParamDTO, ResultConstant.PARAMETERS_CANNOT_BE_NULL);
        Integer pageNo = orderManageParamDTO.getPageNo();
        Integer pageSize = orderManageParamDTO.getPageSize();
        CheckObjects.isPage(pageNo, pageSize);
        String status = orderManageParamDTO.getStatus();
        if (!StringUtils.isEmpty(status)) {
            DataBaseConstant.OrderStatus orderStatus = DataBaseConstant.OrderStatus.find(status);
            CheckObjects.isNull(orderStatus, "订单状态输入有误");
        }

        // 2.订单查询
        Wrapper<Order> entityWrapper = new EntityWrapper<Order>()
                .eq(!StringUtils.isEmpty(orderManageParamDTO.getOrderNo()), "order_no", orderManageParamDTO.getOrderNo())
                .eq(!StringUtils.isEmpty(status), "status", status);

        String startDate = orderManageParamDTO.getStartDate();
        String endDate = orderManageParamDTO.getEndDate();
        if (!StringUtils.isEmpty(startDate)) {
            entityWrapper.andNew().ge("create_date",
                    LocalDateTime.parse(startDate + " " + BaseConstant.FormatDate.TIME_STRING_START,
                            DateTimeFormatter.ofPattern(BaseConstant.FormatDate.DATE_TIME)));
        }
        if (!StringUtils.isEmpty(endDate)) {
            entityWrapper.andNew().le("create_date",
                    LocalDateTime.parse(endDate + " " + BaseConstant.FormatDate.TIME_STRING_END,
                            DateTimeFormatter.ofPattern(BaseConstant.FormatDate.DATE_TIME)));
        }
        List<Order> orders = orderMapper.selectPage(new Page<Order>(pageNo, pageSize),
                entityWrapper.orderBy("create_date", false));

        // 3.结果处理
        List<OrderManageInfoDTO> orderManageInfoDTOS = orders.stream().map(order -> {
            // 3-1.Entity -> DTO
            OrderManageInfoDTO orderManage = BeanCopierUtils.copyBean(order, OrderManageInfoDTO.class);
            // 3-2.分 -> 元
            orderManage.setPayment(PriceUtils.centToYuan(orderManage.getPayment()));

            return orderManage;
        }).collect(Collectors.toList());

        return orderManageInfoDTOS;
    }

    @Override
    public void editOrder(OrderEditParamDTO orderEditParamDTO) {
        // 1.参数校验
        CheckObjects.isNull(orderEditParamDTO, ResultConstant.PARAMETERS_CANNOT_BE_NULL);
        Long orderId = orderEditParamDTO.getOrderId();
        CheckObjects.isNull(orderId, "订单编号不能为空");
        CheckObjects.isNull(orderEditParamDTO.getShippingCode(), "物流单号不能为空");

        // 2.查询订单
        Order o = orderMapper.selectById(orderId);
        CheckObjects.isNull(o, "订单不存在");
        CheckObjects.predicate(o.getStatus(), s -> {
            return DataBaseConstant.OrderStatus.TO_BE_PAID.getCode().equals(s);
        }, "订单未支付");

        // 3.更新物流单号信息
        Order order = new Order();
        if (DataBaseConstant.OrderStatus.TO_BE_SHIPPED.getCode().equals(o.getStatus())) {
            // 3-1.更新订单为待收货
            order.setStatus(DataBaseConstant.OrderStatus.TO_BE_RECEIVED.getCode());
            // 3-2.更新订单发货时间
            order.setConsignDate(LocalDateTime.now());
        }
        // 3-3.订单 物流单号
        order.setShippingCode(orderEditParamDTO.getShippingCode());
        // 3-4.订单更新时间
        order.setUpdateDate(LocalDateTime.now());
        boolean rows = order.update(new EntityWrapper<Order>().eq("id", o.getId()));
    }

    @Override
    public OrderDTO getOrderManageDetail(Long orderId) {
        // 1.参数校验
        CheckObjects.isNull(orderId, "请选择需要查询的订单");
        Order order = orderMapper.selectById(orderId);
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
