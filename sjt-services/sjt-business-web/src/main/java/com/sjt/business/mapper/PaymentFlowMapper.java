package com.sjt.business.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.sjt.business.entity.PaymentFlow;

/**
 * @author: yilan.hu
 * @data: 2019/8/5
 */
public interface PaymentFlowMapper extends BaseMapper<PaymentFlow> {

    /**
     * 查询支付成功的订单
     * @param orderNo
     * @return
     */
    PaymentFlow selectPayByOrderNo(String orderNo);
}
