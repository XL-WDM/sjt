package com.sjt.business.api.expose;

import com.sjt.business.api.dto.req.OrderEditParamDTO;
import com.sjt.business.api.dto.req.OrderManageParamDTO;
import com.sjt.business.api.dto.req.OrderParamDTO;
import com.sjt.business.api.dto.req.PlaceOrderParamDTO;
import com.sjt.business.api.dto.res.OrderDTO;
import com.sjt.business.api.dto.res.OrderManageInfoDTO;
import com.sjt.business.api.dto.res.PlaceOrderDTO;
import com.sjt.common.base.result.ResultDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author: yilan.hu
 * @data: 2019/7/18
 */
@Api(description = "订单", tags = "订单")
@RequestMapping("/order")
public interface OrderApi {

    /**
     * 下单
     * @param placeOrderParamDTO
     * @return
     */
    @ApiOperation(value = "下单")
    @PostMapping("/place-order")
    ResultDTO<PlaceOrderDTO> placeOrder(@RequestBody PlaceOrderParamDTO placeOrderParamDTO);


    /**
     * 订单查询
     * @param orderParamDTO
     * @return
     */
    @ApiOperation(value = "订单查询", response = OrderDTO.class)
    @GetMapping("/list/page")
    ResultDTO<List<OrderDTO>> getOrder(OrderParamDTO orderParamDTO);

    /**
     * 获取订单详情
     * @param orderId
     * @return
     */
    @ApiImplicitParam(name = "orderId", value = "订单编号", required = true)
    @ApiOperation(value = "获取订单详情")
    @GetMapping("/detail")
    ResultDTO<OrderDTO> getOrderDetail(Long orderId);


    /**
     * 订单管理查询
     * @param orderManageParamDTO
     * @return
     */
    @ApiOperation(value = "订单管理查询", response = OrderManageInfoDTO.class, hidden = true)
    @GetMapping("/manage/query")
    ResultDTO<List<OrderManageInfoDTO>> getOrders(OrderManageParamDTO orderManageParamDTO);

    /**
     * 订单管理-物流单号录入
     * @param orderEditParamDTO
     * @return
     */
    @ApiOperation(value = "订单管理-物流单号录入", hidden = true)
    @PostMapping("/manage/logistics/import")
    ResultDTO editOrder(@RequestBody OrderEditParamDTO orderEditParamDTO);

    /**
     * 获取订单管理详情
     * @param orderId
     * @return
     */
    @ApiImplicitParam(name = "orderId", value = "订单编号", required = true)
    @ApiOperation(value = "获取订单管理详情", response = OrderDTO.class)
    @GetMapping("/manage/detail")
    ResultDTO<OrderDTO> getOrderManageDetail(Long orderId);
}
