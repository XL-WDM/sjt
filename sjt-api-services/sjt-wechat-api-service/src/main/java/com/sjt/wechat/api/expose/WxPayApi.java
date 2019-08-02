package com.sjt.wechat.api.expose;

import com.sjt.common.base.result.ResultDTO;
import com.sjt.wechat.api.dto.req.WxPayParamDTO;
import com.sjt.wechat.api.dto.res.WxPayDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

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
     * @param wxPayParamDTO
     * @return
     */
    @ApiOperation(value = "微信小程序统一下单", response = WxPayDTO.class)
    @PostMapping("/unified-order")
    ResultDTO<WxPayDTO> wxH5UnifiedOrder(WxPayParamDTO wxPayParamDTO);

    /**
     * 微信支付异步回调
     * @param notifyData
     * @return
     */
    @ApiOperation(value = "微信支付异步回调")
    @PostMapping("/open-api/notify")
    ModelAndView payNotify(@RequestBody String notifyData, Map map);
}
