package com.stj.business.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author: yilan.hu
 * @data: 2019/5/24
 */
@Data
@Component
@ConfigurationProperties("stj.cross")
public class Cross {
    /**
     * Setting the Cross-domain url
     */
    private String[] origin;

    /**
     * Setting the request headers
     */
    private String[] heders;

    /**
     * Setting the Api white list
     */
    private String[] apiWhiteList;

    /**
     * Setting the methods
     */
    private String[] methods;
}
