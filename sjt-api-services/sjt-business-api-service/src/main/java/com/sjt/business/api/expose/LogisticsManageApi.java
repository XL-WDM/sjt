package com.sjt.business.api.expose;

import com.sjt.common.base.result.ResultDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 物流管理
 * @author: yilan.hu
 * @data: 2019/8/9
 */
@Api(value = "物流管理", tags = "物流管理")
@RequestMapping("/logistics")
public interface LogisticsManageApi {

    /**
     * 物流查询
     * @param orderNo
     * @return
     */
    @ApiImplicitParam(value = "订单号", name = "orderNo")
    @ApiOperation(value = "物流查询")
    @GetMapping("/query")
    ResultDTO queryLogistics(String orderNo);
}
