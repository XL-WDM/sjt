package com.sjt.business.web.interceptor;

import com.sjt.business.entity.User;
import com.sjt.business.entity.UserSignLog;
import com.sjt.business.mapper.UserMapper;
import com.sjt.business.mapper.UserSignLogMapper;
import com.sjt.business.web.config.WebUserContext;
import com.sjt.common.base.result.ResultDTO;
import com.sjt.common.utils.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;

/**
 * @author: yilan.hu
 * @data: 2019/7/9
 */
@Component
public class UserSignatureInterceptors implements HandlerInterceptor {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserSignLogMapper userSignLogMapper;

    /**
     * 预检请求
     */
    private final static String METHOD_TYPE_OPTIONS = "OPTIONS";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 1.预检请求直接通过
        if (METHOD_TYPE_OPTIONS.equals(request.getMethod())) {
            return true;
        }

        // 2.获取请求cookie中的token
        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            ResponseUtils.fallback(response, ResultDTO.error401());
            return false;
        }
        String token = null;
        for (Cookie cookie : cookies) {
            if (WebUserContext.USER_COOKIE.equals(cookie.getName())) {
                token = cookie.getValue();
                break;
            }
        }

        // 3.验证 token
        if (StringUtils.isEmpty(token)) {
            ResponseUtils.fallback(response, ResultDTO.error401());
            return false;
        }
        UserSignLog userSignLog = userSignLogMapper.selectSignLog(token);
        if (userSignLog == null) {
            ResponseUtils.fallback(response, ResultDTO.error401());
            return false;
        }
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime expirationTime = userSignLog.getExpirationTime();
        if (expirationTime.compareTo(now) < 0) {
            ResponseUtils.fallback(response, ResultDTO.error401());
            return false;
        }

        // 4.获取用户信息
        User user = WebUserContext.getCacheUser(userSignLog.getUserId());
        if (user == null) {
            user = userMapper.selectById(userSignLog.getUserId());
            WebUserContext.instance(user);
        }
        WebUserContext.add(user);

        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        WebUserContext.remove();
    }
}
