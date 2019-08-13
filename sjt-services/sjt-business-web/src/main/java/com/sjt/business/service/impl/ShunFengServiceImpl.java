package com.sjt.business.service.impl;

import com.sf.csim.express.service.CallExpressServiceTools;
import com.sf.csim.express.service.VerifyCodeUtil;
import com.sjt.business.config.sf.SfLogistics;
import com.sjt.business.constant.DataBaseConstant;
import com.sjt.business.entity.Order;
import com.sjt.business.mapper.OrderMapper;
import com.sjt.business.service.IShunFengService;
import com.sjt.common.utils.CheckObjects;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author: yilan.hu
 * @data: 2019/8/9
 */
@Slf4j
@Service
public class ShunFengServiceImpl implements IShunFengService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private SfLogistics sfLogistics;

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public Object routeQuery(String orderNo) {
        // 1.参数校验
        CheckObjects.isEmpty(orderNo, "订单号不能为空");
        Order order = orderMapper.selectOneByOrderNo(orderNo);
        CheckObjects.isNull(order, "订单不存在");
        CheckObjects.predicate(order.getStatus(), s -> {
            return DataBaseConstant.OrderStatus.TO_BE_PAID.getCode().equals(s) ||
                   DataBaseConstant.OrderStatus.TO_BE_SHIPPED.getCode().equals(s) ||
                   DataBaseConstant.OrderStatus.CANCELLED.getCode().equals(s);
        }, "当前订单状态无法查询物流");
        String shippingCode = order.getShippingCode();
        CheckObjects.isEmpty(shippingCode, "订单还未创建物流单号");
        String contactPhone = order.getContactPhone();
        CheckObjects.isEmpty(contactPhone, "订单无联系电话");

        // 2.获取配置信息
        String reqXml = sfLogistics.getRouteXml();
        String clientCode = sfLogistics.getClientCode();
        String checkword = sfLogistics.getCheckword();
        String url = sfLogistics.getUrl();

        // 3.处理报文
        reqXml = reqXml.replace("CLIENT_CODE", clientCode)
                       .replace("TRACKING_TYPE", "1")
                       .replace("NUMBER", shippingCode)
                       .replace("CHECK_PHONE_NO", contactPhone.substring(contactPhone.length() - 4));

        log.info("【顺丰物流查询】 request -> {}", reqXml);
        String respXml = CallExpressServiceTools.callSfExpressServiceByCSIM(url, reqXml, clientCode, checkword);
        log.info("【顺丰物流查询】 result -> {}", respXml);

        return respXml;
    }

    private String getVerifyCode(String reqXML, String checkword) {
        return VerifyCodeUtil.md5EncryptAndBase64(reqXML + checkword);
    }
}
