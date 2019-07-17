package com.sjt.config.ratelimiter;

import com.alibaba.fastjson.JSONObject;
import com.sjt.common.base.result.R;
import com.sjt.config.ratelimiter.annotation.RateLimite;
import com.google.common.util.concurrent.RateLimiter;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * @author: yilan.hu
 * @data: 2019/7/1
 */
@Aspect
@Slf4j
public class RateLimitAspect {
    /**
     * 令牌筒集合
     */
    private static ConcurrentHashMap<String, RateLimiter> map = new ConcurrentHashMap<>();

    @Pointcut("@annotation(com.sjt.config.ratelimiter.annotation.RateLimite)")
    public void pointcut() {}

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        Object target = joinPoint.getTarget();
        // 类的完整路径
        String className = target.getClass().getName();

        // 获取被代理方法
        Method method = ((MethodSignature)joinPoint.getSignature()).getMethod();
        // 方法名称
        String methodName = method.getName();

        // 获取@RateLimite注解
        RateLimite annotation = method.getAnnotation(RateLimite.class);
        // 获取速率值
        double rateLimit = annotation.rateLimit();
        // 获取超时时间
        long timeOut = annotation.timeOut();

        // 获取令牌筒
        RateLimiter rateLimiter = createRateLimiter(className.concat(".").concat(methodName), rateLimit);

        // 获取令牌
        boolean b = rateLimiter.tryAcquire(timeOut, TimeUnit.MILLISECONDS);
        if (!b) {
            // 服务降级
            fallback();
            return null;
        }

        // 执行方法
        return joinPoint.proceed();
    }

    /**
     * 获取令牌筒(同步)
     * @param key
     * @param rateLimit
     * @return
     */
    private RateLimiter createRateLimiter(String key, double rateLimit) {
        RateLimiter rateLimiter = map.get(key);
        if (rateLimiter == null) {
            synchronized (map) {
                if(rateLimiter == null) {
                    rateLimiter = RateLimiter.create(rateLimit);
                    map.put(key, rateLimiter);
                }
            }
        }
        return rateLimiter;
    }

    /**
     * 服务降级
     */
    private void fallback() throws IOException {
        // 获取响应对象
        ServletRequestAttributes attributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        HttpServletResponse response = attributes.getResponse();
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        response.getWriter().write(JSONObject.toJSONString(R.error("操作频繁")));
    }
}
