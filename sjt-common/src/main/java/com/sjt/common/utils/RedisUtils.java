package com.sjt.common.utils;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.concurrent.TimeUnit;

/**
 * redis工具类(未完成)
 * @author: yilan.hu
 * @data: 2019/3/14
 */
@Slf4j
@Component
public class RedisUtils {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 设置 key - value Object -> String
     * @param key 键
     * @param o 值
     */
    public void setString(String key, Object o) {
        if (o instanceof String) {
            try {
                redisTemplate.opsForValue().set(key, o);
            } catch (Exception e) {
                log.error("## Redis connect timeout");
            }
        } else {
            setString(key, JSONObject.toJSONString(o));
        }
    }

    /**
     * 设置 key - value 添加过期时间
     * @param key 键
     * @param o 值
     * @param time 过期时间
     * @param timeUnit 单位
     */
    public void setStringTime(String key, Object o, long time, TimeUnit timeUnit) {
        try {
            if (o instanceof String) {
                redisTemplate.opsForValue().set(key, o, time, timeUnit);
            } else {
                redisTemplate.opsForValue().set(key, JSONObject.toJSON(o), time, timeUnit);
            }
        } catch (Exception e) {
            log.error("## Redis connect timeout");
        }
    }

    /**
     * 设置 key - value 添加过期时间, 单位: 秒
     * @param key 键
     * @param o 值
     * @param time 过期时间
     */
    public void setStringTimeSec(String key, Object o, long time) {
        setStringTime(key, o, time, TimeUnit.SECONDS);
    }

    /**
     * 设置 key - value 添加过期时间, 单位: 分钟
     * @param key 键
     * @param o 值
     * @param time 过期时间
     */
    public void setStringTimeMin(String key, Object o, long time) {
        setStringTime(key, o, time, TimeUnit.MINUTES);
    }

    /**
     * 设置 key - value 添加过期时间, 单位: 小时
     * @param key 键
     * @param o 值
     * @param time 过期时间
     */
    public void setStringTimeHour(String key, Object o, long time) {
        setStringTime(key, o, time, TimeUnit.HOURS);
    }

    /**
     * 获取 value(返回值为对象)
     * @param key 键
     * @param t 类型Class对象
     * @param <T> 返回对象类型
     * @return T
     */
    public <T> T getObject(String key, Class<T> t) {
        String result = getString(key);
        return StringUtils.isEmpty(result) ? null : JSONObject.parseObject(getString(key), t);
    }

    /**
     * 获取 value 返回json字符串
     * @param key 键
     * @return String
     */
    public String getString(String key) {
        Object o = null;
        try {
            o = redisTemplate.opsForValue().get(key);
            return o == null ? null : String.valueOf(o);
        } catch (Exception e) {
            log.error("## Redis connect timeout");
            return null;
        }
    }
}
