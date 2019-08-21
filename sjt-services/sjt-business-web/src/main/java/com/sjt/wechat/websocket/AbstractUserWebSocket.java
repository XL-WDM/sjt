package com.sjt.wechat.websocket;

import com.sjt.business.web.config.WebUserContext;
import lombok.extern.slf4j.Slf4j;

import javax.websocket.*;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author: yilan.hu
 * @data: 2019/8/21
 */
@Slf4j
public abstract class AbstractUserWebSocket {

    private Session session;

    private final CopyOnWriteArraySet<AbstractUserWebSocket> webSockets = new CopyOnWriteArraySet<>();

    private final Map<Long, AbstractUserWebSocket> userMapping = new ConcurrentHashMap<>();

    protected CopyOnWriteArraySet<AbstractUserWebSocket> getWebSockets() {
        return webSockets;
    }

    protected Session getSession () {
        return session;
    }

    /**
     * 建立连接
     * @param session
     */
    @OnOpen
    public final void onOpen(Session session, EndpointConfig config) {
        String cookie = (String)config.getUserProperties().get(WebUserContext.USER_COOKIE);

        this.session = session;
        webSockets.add(this);
        Long userId = getUserId(cookie);
        if (userId != null) {
            userMapping.put(userId, this);
        }
    }

    /**
     * 连接关闭
     */
    @OnClose
    public final void onClose() {
        webSockets.remove(this);
        close();
    }
    /**
     * 接收客户端消息
     * @param message
     */
    @OnMessage
    public final void onMessage(String message) {
        message(message);
    }

    /**
     * 抽象方法-获取用户
     * @param token
     * @return
     */
    protected abstract Long getUserId(String token);

    /**
     * 抽象方法-连接关闭
     */
    protected abstract void close();


    /**
     * 抽象方法-接收客户端消息
     * @param message
     */
    protected abstract void message(String message);

    /**
     * 广播消息
     * @param t
     */
    protected final void sendMessage(String message) throws Exception {
        this.session.getBasicRemote().sendText(message);
    }

    /**
     * 广播消息
     * @param t
     */
    protected final AbstractUserWebSocket getUserWebSocket(Long userId) {
        return userMapping.get(userId);
    }
}
