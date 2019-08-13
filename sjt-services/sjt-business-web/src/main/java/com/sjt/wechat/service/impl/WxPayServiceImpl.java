package com.sjt.wechat.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.sjt.business.constant.DataBaseConstant;
import com.sjt.business.entity.*;
import com.sjt.business.mapper.*;
import com.sjt.business.web.config.WebUserContext;
import com.sjt.common.base.constant.BaseConstant;
import com.sjt.common.base.constant.ResultConstant;
import com.sjt.common.utils.*;
import com.sjt.wechat.api.dto.req.WxPayParamDTO;
import com.sjt.wechat.api.dto.res.WxPayDTO;
import com.sjt.wechat.config.WxBaseInfo;
import com.sjt.wechat.constant.WechatConstant;
import com.sjt.wechat.service.IWxPayService;
import com.sjt.wechat.utils.PaySignatureUtils;
import com.sjt.wechat.vo.req.pay.WxPayUnifiedRequestVO;
import com.sjt.wechat.vo.res.pay.WxPayNotifyResponseVO;
import com.sjt.wechat.vo.res.pay.WxPayUnifiedResponseVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: yilan.hu
 * @data: 2019/7/31
 */
@Slf4j
@Service
public class WxPayServiceImpl implements IWxPayService {

    @Autowired
    private WxBaseInfo wxBaseInfo;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderItemMappler orderItemMappler;

    @Autowired
    private AddressMapper addressMapper;

    @Autowired
    private UserOauthsMapper userOauthsMapper;

    @Autowired
    private PaymentFlowMapper paymentFlowMapper;

    @Autowired
    private ProductSpecMapper productSpecMapper;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private SnowflakeIdUtils snowflakeIdUtils;

    private final String PAY_ORDER_BODY = "山尖田-订单编号";

    /** 重试次数 */
    private final int retryCount = 5;

    @Override
    public WxPayDTO wxH5UnifiedOrder(WxPayParamDTO wxPayParamDTO) {
        // 1.参数校验
        CheckObjects.isNull(wxPayParamDTO, ResultConstant.PARAMETERS_CANNOT_BE_NULL);
        CheckObjects.isNull(wxPayParamDTO.getOrderId(), "请选择订单");
        CheckObjects.isNull(wxPayParamDTO.getAddressId(), "请选择物流地址");

        // 2.查询订单
        Order order = orderMapper.selectById(wxPayParamDTO.getOrderId());
        CheckObjects.isNull(order, "订单不存在");
        CheckObjects.predicate(order.getStatus(),
                s -> DataBaseConstant.OrderStatus.CANCELLED.getCode().equals(s),
                "订单已失效");
        CheckObjects.predicate(order.getStatus(),
                s -> !DataBaseConstant.OrderStatus.TO_BE_PAID.getCode().equals(s),
                "重复支付");

        // 3.查询该订单是否存支付成功的流水
        PaymentFlow paymentFlows = paymentFlowMapper.selectPayByOrderNo(order.getOrderNo());
        CheckObjects.predicate(paymentFlows,
                pfs -> pfs != null && DataBaseConstant.PayStatus.PAYMENTED.getCode().equals(pfs.getStatus()),
                "重复支付");

        // 4.查询物流地址
        Long userId = WebUserContext.getContext().getId();
        Address address = addressMapper.selectAddressByIdAndUserId(wxPayParamDTO.getAddressId(), userId);
        CheckObjects.isNull(address, "物流地址不存在");

        // 5.获取用户openid
        UserOauths userOauths = userOauthsMapper.selectOneByUserIdAndType(String.valueOf(userId),
                DataBaseConstant.OauthType.WX_APPLET.getCode());
        CheckObjects.isNull(userOauths, "用户 openid 为空");

        // 6.更新物流信息
        order.setContactName(address.getContacts());
        order.setContactPhone(address.getPhone());
        StringBuilder build = new StringBuilder()
                .append(address.getProvince())
                .append(address.getCity())
                .append(address.getCounty())
                .append(address.getAddress())
                .append(address.getDoorNumber());
        order.setAddress(build.toString());
        order.updateById();

        // 7.支付信息封装
        String nonceStr = RandomUtils.getRandomString();
        WxPayUnifiedRequestVO vo = new WxPayUnifiedRequestVO();
        vo.setAppid(wxBaseInfo.getAppletId());
        vo.setMchId(wxBaseInfo.getMchId());
        vo.setSignType(BaseConstant.Character.MD5);
        vo.setBody(PAY_ORDER_BODY.concat(order.getOrderNo()));
        vo.setOutTradeNo(order.getOrderNo());
        vo.setNotifyUrl(wxBaseInfo.getNotifyUrl());
        vo.setFeeType(BaseConstant.FeeType.CNY);
        vo.setTotalFee(order.getPayment().intValue());
        vo.setSpbillCreateIp(LocalUtils.getIp());
        vo.setTradeType("JSAPI");
        vo.setProductId(order.getOrderNo());
        vo.setOpenid(userOauths.getOauthId());
        vo.setNonceStr(nonceStr);
        String sign = PaySignatureUtils.wxSign(MapUtils.buildMap(vo), wxBaseInfo.getMchSecret());
        vo.setSign(sign);

        String requestXml = XmlUtils.toString(vo);
        CheckObjects.isEmpty(requestXml, "参数处理失败");

        // 8.发起支付
        log.info("【微信支付】 request -> {}", requestXml);
        ResponseEntity<String> entity = restTemplate.postForEntity(WechatConstant.UNIFIED_ORDER,
                requestXml,
                String.class);
        CheckObjects.predicate(entity.getStatusCode(), status -> HttpStatus.OK != status,
                "微信支付发起失败, 网络异常",
                () -> {log.error("## 【微信支付发起失败】, 网络异常 -> status: {}", entity.getStatusCodeValue());});
        // 8-1.获取body
        String responseXml = entity.getBody();
        log.info("【微信支付】 response -> {}", responseXml);

        // 8-2.xml -> 对象
        WxPayUnifiedResponseVO payResponse = XmlUtils.toObject(responseXml, WxPayUnifiedResponseVO.class);
        CheckObjects.isNull(payResponse, "微信支付发起失败", () -> {log.error("## 【微信支付】 报文解析异常");});
        CheckObjects.predicate(payResponse.isFail(), b -> b,
                "微信支付发起失败, return_msg: " + payResponse.getReturnMsg(),
                () -> {log.error("## 【微信支付发起失败】 return_msg -> {}", payResponse.getReturnMsg());});
        CheckObjects.predicate(payResponse.isSuccess(), b -> !b,
                "微信支付发起失败, err_code: " + payResponse.getErrCode(),
                () -> {log.error("## 【微信支付发起失败】 err_code -> {}", payResponse.getReturnMsg());});

        // 9.创建支付流水
        if (paymentFlows == null) {
            PaymentFlow paymentFlow = new PaymentFlow();
            paymentFlow.setPayNo(String.valueOf(snowflakeIdUtils.getId()));
            paymentFlow.setOrderNo(order.getOrderNo());
            paymentFlow.setUserId(userId);
            paymentFlow.setAmount(order.getPayment());
            paymentFlow.setPayType(DataBaseConstant.PayType.WECHAT.getCode());
            paymentFlow.setStatus(DataBaseConstant.PayStatus.UNPAID.getCode());
            paymentFlow.insert();
        }

        // 10.签名处理
        String timeStamp = String.valueOf(System.currentTimeMillis());
        String packAge = "prepay_id" + BaseConstant.Character.EQUAL + payResponse.getPrepayId();
        String resultNonceStr = payResponse.getNonceStr();

        // 10-1.创建map
        Map<String, String> map = new HashMap(8);
        map.put("appId", payResponse.getAppid());
        map.put("timeStamp", timeStamp);
        map.put("nonceStr", resultNonceStr);
        map.put("package", packAge);
        map.put("signType", BaseConstant.Character.MD5);

        // 10-2.生成签名
        String resultSign = PaySignatureUtils.wxSign(map, wxBaseInfo.getMchSecret());

        // 11.处理结果
        WxPayDTO wxPayDTO = new WxPayDTO();
        wxPayDTO.setNonceStr(resultNonceStr);
        wxPayDTO.setSignType(BaseConstant.Character.MD5);
        wxPayDTO.setTimeStamp(timeStamp);
        wxPayDTO.setPackAge(packAge);
        wxPayDTO.setPaySign(resultSign);

        return wxPayDTO;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void payNotify(String notifyData) {
        log.info("【微信支付结果通知】 request -> {}", notifyData);

        // 1.参数校验
        CheckObjects.isNull(notifyData, ResultConstant.PARAMETERS_CANNOT_BE_NULL);

        // 2.签名验证
        CheckObjects.predicate(PaySignatureUtils.vxVerify(XmlUtils.toMap(notifyData), wxBaseInfo.getMchSecret()),
                b -> !b, "签名验证失败", () -> {log.error("## 【微信支付结果通知】 签名验证失败");});

        // 3.xml转对象
        WxPayNotifyResponseVO wxPayNotifyResponseVO = XmlUtils.toObject(notifyData, WxPayNotifyResponseVO.class);
        CheckObjects.isNull(wxPayNotifyResponseVO, "报文解析失败",
                () -> {log.error("## 【微信支付结果通知】 报文解析失败");});

        if (wxPayNotifyResponseVO.isFail()) {
            log.error("## 【微信支付结果通知】 微信返回失败 result_code = FAIL");
            return;
        }

        String orderNo = wxPayNotifyResponseVO.getOutTradeNo();

        // 5.获取订单
        Order order = orderMapper.selectOneByOrderNo(orderNo);
        if (order == null) {
            log.error("## 【微信支付结果通知】 订单不存在, orderNo: {}", orderNo);
            return;
        }

        // 6.查询流水
        PaymentFlow paymentFlow = paymentFlowMapper.selectPayByOrderNo(order.getOrderNo());
        if (paymentFlow == null) {
            log.error("## 【微信支付结果通知】 支付流水不存在, orderNo: {}", orderNo);
            return;
        }
        if (DataBaseConstant.PayStatus.PAYMENTED.getCode().equals(paymentFlow.getStatus())) {
            log.error("## 【微信支付结果通知】 重复通知, orderNo: {}", orderNo);
            return;
        }

        // 7.修改订单状态为已支付
        order.setStatus(DataBaseConstant.OrderStatus.TO_BE_SHIPPED.getCode());
        order.setUpdateDate(LocalDateTime.now());
        LocalDateTime paymentDate = LocalDateTime.parse(wxPayNotifyResponseVO.getTimeEnd(),
                DateTimeFormatter.ofPattern(BaseConstant.FormatDate.SIMPLE_DATE_TIME));
        order.setPaymentDate(paymentDate);
        order.updateById();

        // 8.更新支付流水为已支付
        paymentFlow.setPayNo(wxPayNotifyResponseVO.getTransactionId());
        paymentFlow.setStatus(DataBaseConstant.PayStatus.PAYMENTED.getCode());
        paymentFlow.setUpdateDate(LocalDateTime.now());
        paymentFlow.setPayCompleteDate(paymentDate);
        paymentFlow.updateById();

        // 9.修改库存
        // 9-1.查询订单详情
        List<OrderItem> orderItems = orderItemMappler.selectList(new EntityWrapper<OrderItem>()
                .eq("order_id", order.getId()));
        for (OrderItem orderItem : orderItems) {
            for (int i = 0; i < retryCount; i++) {
                // 9-2.查询商品规格
                ProductSpec productSpec = productSpecMapper.selectById(orderItem.getProductSpecId());
                // 9-3.减库存
                int version = productSpec.getVersion();
                ProductSpec ps = new ProductSpec();
                ps.setStockNum(productSpec.getStockNum() - orderItem.getNum());
                ps.setOrderStockNum(productSpec.getOrderStockNum() - orderItem.getNum());
                ps.setVersion(version + 1);
                boolean b = ps.update(new EntityWrapper()
                        .eq("id", productSpec.getId())
                        .eq("version", version));
                if (b) {
                    break;
                }
            }
        }
    }
}
