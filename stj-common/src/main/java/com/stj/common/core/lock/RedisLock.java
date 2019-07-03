package com.stj.common.core.lock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * redis 分布式锁
 * @author: yilan.hu
 * @data: 2019/5/24
 */
@Component
public class RedisLock {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 默认获取锁时间
     */
    private final long DEFAULT_CONNECT_OUT_TIME = 1000L;
    /**
     * 获取锁、解锁成功时的结果
     */
    private final String LOCK_SUCCESS = "SUCCESS";
    /**
     * 获取锁、解锁失败时的结果
     */
    private final String LOCK_ERROR = "ERROR";

    /**
     * 加锁
     * @param key key值
     * @param connectOutTime
     * @param readOutTime
     * @return
     */
    public String getLock(String key, Long connectOutTime, Long holdTime) {
        // key不能为空
        if (key == null || "".equals(key)) {
            return null;
        }

        // 获取所时间不能小于默认值
        if (connectOutTime == null || connectOutTime < DEFAULT_CONNECT_OUT_TIME) {
            connectOutTime = DEFAULT_CONNECT_OUT_TIME;
        }

        // 锁持有时间不能为空
        if (holdTime == null) {
            return null;
        }

        // 获取value值
        String value = UUID.randomUUID().toString();

        try {
            // 获取锁超时时间
            long getLockOutTime = System.currentTimeMillis() + connectOutTime;

            while (System.currentTimeMillis() <= getLockOutTime) {
                // 加锁(事务操作, 保证原子性)
                String result = redisTemplate.execute((RedisConnection connection) -> {
                    if (connection.setNX(getBytes(key), getBytes(value)) &&
                            connection.expire(getBytes(key), holdTime / 1000)) {
                        return LOCK_SUCCESS;
                    }
                    return LOCK_ERROR;
                });
                if (LOCK_SUCCESS.equals(result)) {
                    return value;
                }
            }
        } catch (Exception e) {

        }
        return null;
    }

    /**
     * 释放锁
     * @param key
     * @return
     */
    public boolean unLock(String key, String value) {
        try {
            if (key == null || "".equals(key)) {
                return false;
            }
            if (value == null || "".equals(value)) {
                return false;
            }

            //  释放锁(事务操作, 保证原子性)
            String result = redisTemplate.execute((RedisConnection connection) -> {
                byte[] keyByte = connection.get(getBytes(key));
                if (value.equals(getString(keyByte))) {
                    Long l = connection.del(getBytes(key));
                    return l > 0 ? LOCK_SUCCESS : LOCK_ERROR;
                }
                return LOCK_ERROR;
            });
            if (LOCK_SUCCESS.equals(result)) {
                return true;
            }
        } catch (Exception e) {

        }
        return false;
    }

    private byte[] getBytes(String str) {
        return redisTemplate.getStringSerializer().serialize(str);
    }

    private String getString(byte[] bytes) {
        return redisTemplate.getStringSerializer().deserialize(bytes);
    }
}
