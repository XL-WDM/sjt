package com.sjt.wechat.websocket;

import com.sjt.business.entity.Order;
import com.sjt.business.entity.User;
import com.sjt.business.entity.UserSignLog;
import com.sjt.business.mapper.OrderMapper;
import com.sjt.business.mapper.UserMapper;
import com.sjt.business.mapper.UserSignLogMapper;
import com.sjt.common.utils.CheckObjects;
import com.sjt.wechat.websocket.config.WebSocketConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.websocket.EndpointConfig;
import javax.websocket.server.ServerEndpoint;

/**
 * @author: yilan.hu
 * @data: 2019/8/18
 */
@Slf4j
@Component
@ServerEndpoint(value = "/order-pay/notify/websocket", configurator = WebSocketConfig.class)
public class OrderPayNotifyWebSocket extends AbstractUserWebSocket {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserSignLogMapper userSignLogMapper;

    @Autowired
    private OrderMapper orderMapper;

    @Override
    protected Long getUserId(String token) {
        log.info(" [WebSocket-订单支付通知] 有新的连接, 总数: {}", getWebSockets().size());
        UserSignLog userSignLog = userSignLogMapper.selectSignLog(token);
        if (userSignLog != null) {
            return userSignLog.getUserId();
        }
        return null;
    }

    /**
     * 连接关闭
     */
    @Override
    public void close() {
        log.info(" [WebSocket-订单支付通知] 断开连接, 总数: {}", getWebSockets().size());
    }

    /**
     * 接收客户端消息
     * @param message
     */
    @Override
    public void message(String message) {
        log.info(" [WebSocket-订单支付通知] 收到客户端发来的消息: {}", message);
    }

    /**
     * 广播消息
     * @param orderNo
     */
    public void send(String orderNo) {
        Order order = orderMapper.selectOneByOrderNo(orderNo);
        CheckObjects.isNull(order, "订单不存在");

        log.info(" [WebSocket-订单支付通知] 广播消息, message: {}", orderNo);
        try {
            AbstractUserWebSocket userWebSocket = this.getUserWebSocket(order.getUserId());
            CheckObjects.isNull(userWebSocket, "客户端不存在或者已经关闭");
            userWebSocket.sendMessage(orderNo);
        } catch (Exception e) {
            log.error("## [WebSocket-订单支付通知] 消息发送失败, orderNo: {}", orderNo, e);
        }
    }
}
