package com.sjt.wechat.service;

import com.sjt.wechat.api.dto.req.WxPayParamDTO;
import com.sjt.wechat.api.dto.res.WxPayDTO;

/**
 * @author: yilan.hu
 * @data: 2019/7/23
 */
public interface IWxPayService {

    /**
     * 小程序统一下单
     * @param wxPayParamDTO
     * @return
     */
    WxPayDTO wxH5UnifiedOrder(WxPayParamDTO wxPayParamDTO);
}
