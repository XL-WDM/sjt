package com.sjt.business.service.impl;

import com.sf.csim.express.service.CallExpressServiceTools;
import com.sf.csim.express.service.VerifyCodeUtil;
import com.sjt.business.api.dto.res.RouteInfoDTO;
import com.sjt.business.api.dto.res.SfRouteDTO;
import com.sjt.business.config.sf.SfLogistics;
import com.sjt.business.constant.DataBaseConstant;
import com.sjt.business.entity.Order;
import com.sjt.business.mapper.OrderMapper;
import com.sjt.business.service.IShunFengService;
import com.sjt.business.service.bo.sf.SfResponse;
import com.sjt.business.service.bo.sf.SfResponseBody;
import com.sjt.business.service.bo.sf.SfRoute;
import com.sjt.business.service.bo.sf.SfRouteResponse;
import com.sjt.business.web.config.WebUserContext;
import com.sjt.common.utils.BeanCopierUtils;
import com.sjt.common.utils.CheckObjects;
import com.sjt.common.utils.JsonUtils;
import com.sjt.common.utils.XmlUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

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
    public RouteInfoDTO platformRouteQuery(String orderNo) {

        // 1.参数校验
        CheckObjects.isEmpty(orderNo, "订单号不能为空");
        Order order = orderMapper.selectOneByOrderNo(orderNo);
        CheckObjects.isNull(order, "订单不存在");
        CheckObjects.predicate(order.getUserId(),
                id -> !id.equals(WebUserContext.getContext().getId()),
                "订单不存在");
        CheckObjects.predicate(order.getStatus(), s -> {
            return DataBaseConstant.OrderStatus.TO_BE_PAID.getCode().equals(s) ||
                    DataBaseConstant.OrderStatus.TO_BE_SHIPPED.getCode().equals(s) ||
                    DataBaseConstant.OrderStatus.CANCELLED.getCode().equals(s);
        }, "当前订单状态无法查询物流");

        RouteInfoDTO routeInfoDTO = new RouteInfoDTO();
        routeInfoDTO.setShippingName(order.getShippingName());
        routeInfoDTO.setShippingCode(order.getShippingCode());
        List<SfRouteDTO> sfRouteDTOS = routeQuery(order.getShippingCode(), order.getContactPhone());
        routeInfoDTO.setRoutes(sfRouteDTOS);

        return routeInfoDTO;
    }

    @Override
    public List<SfRouteDTO> manageRouteQuery(String orderNo) {
        // 1.参数校验
        CheckObjects.isEmpty(orderNo, "订单号不能为空");
        Order order = orderMapper.selectOneByOrderNo(orderNo);
        CheckObjects.isNull(order, "订单不存在");
        CheckObjects.predicate(order.getStatus(), s -> {
            return DataBaseConstant.OrderStatus.TO_BE_PAID.getCode().equals(s) ||
                   DataBaseConstant.OrderStatus.TO_BE_SHIPPED.getCode().equals(s) ||
                   DataBaseConstant.OrderStatus.CANCELLED.getCode().equals(s);
        }, "当前订单状态无法查询物流");

        // 物流查询
        List<SfRouteDTO> sfRouteDTOS = routeQuery(order.getShippingCode(), order.getContactPhone());

        return sfRouteDTOS;
    }

    /**
     * 物流查询
     * @param shippingCode 物流单号
     * @param contactPhone 寄件人或者收件人手机号
     * @return
     */
    private List<SfRouteDTO> routeQuery(String shippingCode, String contactPhone) {
        // 1.参数校验
        CheckObjects.isEmpty(shippingCode, "订单还未创建物流单号");
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

        log.info("【顺丰路由查询】 request -> {}", reqXml);
        String respXml = CallExpressServiceTools.callSfExpressServiceByCSIM(url, reqXml, clientCode, checkword);
        log.info("【顺丰路由查询】 result -> {}", respXml);
        CheckObjects.isEmpty(respXml, "物流查询失败",
                () -> {log.error("## 【顺丰路由查询】 发起失败, Response XML is empty");});

        SfResponse sfResponse = XmlUtils.toObject(respXml, SfResponse.class);
        CheckObjects.isNull(sfResponse, "物流查询失败");
        CheckObjects.predicate(sfResponse.isSuccess(), succ -> !succ,
                "物流查询失败",
                () -> {log.error("## 【顺丰路由查询】 发起失败 -> message: {}", JsonUtils.toJson(sfResponse.getError()));});
        Optional<SfResponseBody> bodyOpt = Optional.ofNullable(sfResponse.getBody());
        SfRouteResponse routeResponse = bodyOpt.orElse(new SfResponseBody()).getRouteResponse();
        Optional<SfRouteResponse> routeResOpt = Optional.ofNullable(routeResponse);
        List<SfRoute> routes = routeResOpt.orElse(new SfRouteResponse()).getRoutes();
        if (routes == null || routes.isEmpty()) {
            return new ArrayList<SfRouteDTO>();
        }

        // 4.BO -> DTO 并根据路由节点时间倒序排序
        TreeMap<String, SfRouteDTO> routeMap = new TreeMap<>();
        routes.stream().forEach(route -> {
            // 4-1.BO -> DTO
            SfRouteDTO sfRouteDTO = BeanCopierUtils.copyBean(route, SfRouteDTO.class);
            routeMap.put(route.getAcceptTime(), sfRouteDTO);
        });
        // 4-2.倒序
        NavigableMap<String, SfRouteDTO> descRouteMap = routeMap.descendingMap();
        List<SfRouteDTO> sfRouteDTOS = new ArrayList<>();
        descRouteMap.forEach((k, v) -> {
            sfRouteDTOS.add(v);
        });

        return sfRouteDTOS;
    }

    private String getVerifyCode(String reqXML, String checkword) {
        return VerifyCodeUtil.md5EncryptAndBase64(reqXML + checkword);
    }
}
