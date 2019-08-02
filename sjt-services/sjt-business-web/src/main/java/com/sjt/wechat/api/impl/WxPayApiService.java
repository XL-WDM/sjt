package com.sjt.wechat.api.impl;

import com.sjt.common.base.result.ResultDTO;
import com.sjt.wechat.api.dto.req.WxPayParamDTO;
import com.sjt.wechat.api.dto.res.WxPayDTO;
import com.sjt.wechat.api.expose.WxPayApi;
import com.sjt.wechat.service.IWxPayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * @author: yilan.hu
 * @data: 2019/7/31
 */
@RestController
public class WxPayApiService implements WxPayApi {

    @Autowired
    private IWxPayService iWxPayService;

    @Override
    public ResultDTO<WxPayDTO> wxH5UnifiedOrder(@RequestBody WxPayParamDTO wxPayParamDTO) {
        WxPayDTO wxPayDTO = iWxPayService.wxH5UnifiedOrder(wxPayParamDTO);
        return ResultDTO.data(wxPayDTO);
    }

    @Override
    public ModelAndView payNotify(@RequestBody String notifyData, Map map) {
        try {
            iWxPayService.payNotify(notifyData);
        } catch (Exception e) {
            map.put("code", "FAIL");
            map.put("message", "handler error: " + e.getMessage());
            return new ModelAndView("pay/pay-notify-fail", map);
        }

        return new ModelAndView("pay/pay-notify-success");
    }
}
