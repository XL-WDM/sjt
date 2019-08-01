package com.sjt.wechat.config.annotation;


import com.sjt.config.mybatis.plus.MybatisPlusConfiguration;
import com.sjt.wechat.config.PayConfig;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import({PayConfig.class})
public @interface EnableBestPay {
}
