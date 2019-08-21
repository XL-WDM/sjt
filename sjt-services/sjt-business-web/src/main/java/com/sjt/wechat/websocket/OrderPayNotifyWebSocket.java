package com.sjt.wechat.websocket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author: yilan.hu
 * @data: 2019/8/18
 */
@Slf4j
@Component
@ServerEndpoint("/order-pay/notify/websocket")
public class OrderPayNotifyWebSocket {

    private Session session;

    private static CopyOnWriteArraySet<OrderPayNotifyWebSocket> webSockets = new CopyOnWriteArraySet<>();

    /**
     * 建立连接
     * @param session
     */
    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        webSockets.add(this);
        log.info(" [WebSocket-订单支付通知] 有新的连接, 总数: {}", webSockets.size());
    }

    /**
     * 连接关闭
     */
    @OnClose
    public void onClose() {
        webSockets.remove(this);
        log.info(" [WebSocket-订单支付通知] 断开连接, 总数: {}", webSockets.size());
    }

    /**
     * 接收客户端消息
     * @param message
     */
    @OnMessage
    public void onMessage(String message) {
        log.info(" [WebSocket-订单支付通知] 收到客户端发来的消息: {}", message);
    }

    /**
     * 广播消息
     * @param orderNo
     */
    public void sendMessage(String orderNo) {
        for (OrderPayNotifyWebSocket webSocket : webSockets) {
            log.info(" [WebSocket-订单支付通知] 广播消息, message: {}", orderNo);
            try {
                webSocket.session.getBasicRemote().sendText(orderNo);
            } catch (Exception e) {
                log.error("## [WebSocket-订单支付通知] 消息发送失败, orderNo: {}", orderNo);
            }
        }
    }
}
