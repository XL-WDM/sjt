package com.stj.common.config.rateLimiter.annotation;

import java.lang.annotation.*;

/**
 * @author: yilan.hu
 * @data: 2019/7/1
 */
@Inherited
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RateLimite {
    double rateLimit() default 20;
    long timeOut() default 5000;
}
