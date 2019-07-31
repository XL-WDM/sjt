package com.sjt.wechat.api.impl;

import com.sjt.common.base.result.ResultDTO;
import com.sjt.wechat.api.expose.WxPayApi;
import com.sjt.wechat.service.IWxPayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: yilan.hu
 * @data: 2019/7/31
 */
@RestController
public class WxPayApiService implements WxPayApi {

    @Autowired
    private IWxPayService iWxPayService;

    @Override
    public ResultDTO<String> appletUnifiedOrder() {
        String s = iWxPayService.appletUnifiedOrder();
        return ResultDTO.data(s);
    }
}
