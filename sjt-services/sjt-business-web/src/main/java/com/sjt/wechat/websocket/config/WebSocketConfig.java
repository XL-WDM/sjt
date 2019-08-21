package com.sjt.wechat.websocket.config;

import com.sjt.business.web.config.WebUserContext;

import javax.servlet.http.HttpSession;
import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: yilan.hu
 * @data: 2019/8/21
 */
public class WebSocketConfig extends ServerEndpointConfig.Configurator {

    @Override
    public void modifyHandshake(ServerEndpointConfig sec, HandshakeRequest request, HandshakeResponse response) {
        Map<String, List<String>> headers = request.getHeaders();
        List<String> cookie = headers.get("cookie");
        if (cookie != null && !cookie.isEmpty()) {
            for (String s : cookie) {
                Map<String, String> cookieMap = getCookieMap(s, "; ");
                String session = cookieMap.get(WebUserContext.USER_COOKIE);
                if (session != null) {
                    sec.getUserProperties().put(WebUserContext.USER_COOKIE, session);
                }
            }
        }

        super.modifyHandshake(sec, request, response);
    }

    private Map<String, String> getCookieMap(String cookieStr, String joinStr) {
        Map<String, String> map = new HashMap<>();

        String[] split = cookieStr.split(joinStr);
        for (String s : split) {
            String[] keyValue = s.split("=");
            if (keyValue != null && keyValue.length == 2) {
                map.put(keyValue[0], keyValue[1]);
            }
        }

        return map;
    }
}
