package com.sjt.business.api.impl;

import com.sjt.business.api.dto.req.OrderParamDTO;
import com.sjt.business.api.expose.OrderApi;
import com.sjt.common.base.result.ResultDTO;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author: yilan.hu
 * @data: 2019/7/18
 */
@RestController
public class OrderApiService implements OrderApi {

    @Override
    public ResultDTO placeOrder(@RequestBody List<OrderParamDTO> orders) {
        return null;
    }
}
