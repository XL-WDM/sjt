package com.stj.common.config.rateLimiter.annotation;

import com.stj.common.config.rateLimiter.RateLimitAspect;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author: yilan.hu
 * @data: 2019/7/1
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import({RateLimitAspect.class})
public @interface EnableRateLimiter {

}
