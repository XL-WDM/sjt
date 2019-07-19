package com.sjt.business.api.expose;

import com.sjt.business.api.dto.req.OrderParamDTO;
import com.sjt.business.api.dto.req.PlaceOrderParamDTO;
import com.sjt.business.api.dto.res.OrderDTO;
import com.sjt.common.base.result.ResultDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author: yilan.hu
 * @data: 2019/7/18
 */
@Api(description = "订单")
@RequestMapping("/order")
public interface OrderApi {

    /**
     * 下单
     * @param placeOrderParamDTO
     * @return
     */
    @ApiOperation(value = "下单")
    @PostMapping("/place-order")
    ResultDTO placeOrder(PlaceOrderParamDTO placeOrderParamDTO);


    /**
     * 订单查询
     * @param orderParamDTO
     * @return
     */
    @ApiOperation(value = "订单查询")
    @GetMapping("/list/page")
    ResultDTO<List<OrderDTO>> getOrder(OrderParamDTO orderParamDTO);

    /**
     * 获取订单详情
     * @param orderId
     * @return
     */
    @ApiOperation(value = "获取订单详情")
    @GetMapping("/detail")
    ResultDTO<OrderDTO> getOrderDetail(Long orderId);
}
