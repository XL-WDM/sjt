package com.sjt.config.websocket.annotation;

import com.sjt.config.websocket.WebSocketConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 开启 mytais-plus 插件功能
 * @author: yilan.hu
 * @data: 2019/7/1
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import({WebSocketConfiguration.class})
public @interface EnableWebSocket {

}
