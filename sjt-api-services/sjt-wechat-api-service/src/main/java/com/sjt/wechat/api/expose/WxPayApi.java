package com.sjt.wechat.api.expose;

import com.sjt.common.base.result.ResultDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 微信支付
 * @author: yilan.hu
 * @data: 2019/7/31
 */
@Api(value = "微信支付", tags = "微信支付")
@RequestMapping("/wx/pay")
public interface WxPayApi {

    /**
     * 微信小程序统一下单
     * @return
     */
    @ApiOperation(value = "微信小程序统一下单")
    @PostMapping("/unified-order")
    ResultDTO<String> appletUnifiedOrder();
}
