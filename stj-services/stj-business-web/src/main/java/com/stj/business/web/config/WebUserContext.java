package com.stj.business.web.config;

import com.stj.business.entity.User;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author: yilan.hu
 * @data: 2019/7/8
 */
public class WebUserContext {

    public final static String USER_COOKIE = "SESSION";
    private final static String USER_CACHE_KEY = "USER_CACHE_KEY_";

    /**
     * 暂时用来替代redis
     */
    private static Map<String, User> userCache = new ConcurrentHashMap();

    private static ThreadLocal<User> userContext = new ThreadLocal();

    public final static User instance(User user) {
        if (user == null || user.getId() == null) {
            return null;
        }

        // 添加缓存
        userCache.put(USER_CACHE_KEY + user.getId(), user);

        return user;
    }

    public static User getCacheUser(Long id) {
        return userCache.get(USER_CACHE_KEY + id);
    }

    public static User getUser() {
        return userContext.get();
    }

    public static void add(User user) {
        userContext.set(user);
    }

    public static void remove() {
        userContext.remove();
    }
}
