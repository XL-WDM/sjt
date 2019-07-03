package com.stj.common.config.swagger;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * swagger配置对象
 * @author: yilan.hu
 * @data: 2019/7/1
 */
@Data
@Component
@ConfigurationProperties("stj.swagger")
public class SwaggerApiInfo {
    /**
     * Enable the swagger
     */
    private Boolean enable = false;

    /**
     * API document sweep path
     */
    private String basePackage;

    /**
     * Main title
     */
    private String title;

    /**
     * subtitle
     */
    private String description;

    /**
     * Project version
     */
    private String version;

    /**
     * License
     */
    private String license;

    /**
     * License url
     */
    private String licenseUrl;
}
