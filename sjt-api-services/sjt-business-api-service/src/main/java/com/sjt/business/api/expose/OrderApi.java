package com.sjt.business.api.expose;

import com.sjt.business.api.dto.req.OrderParamDTO;
import com.sjt.common.base.result.ResultDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
     * @param orders
     * @return
     */
    @ApiOperation(value = "下单")
    @PostMapping("/place-order")
    ResultDTO placeOrder(List<OrderParamDTO> orders);
}
