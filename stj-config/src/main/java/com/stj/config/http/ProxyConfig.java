package com.stj.config.http;

import com.stj.config.http.tools.HttpTools;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author: yilan.hu
 * @data: 2019/7/1
 */
@Data
@Component
@ConfigurationProperties(prefix = "stj.proxy")
public class ProxyConfig {

    /**
     * Enable ssh check
     */
    private Boolean sshCheck = true;

    /**
     * Enable proxy setting
     */
    private Boolean enableProxy = false;

    /**
     * Proxy host name
     */
    private String host = "0.0.0.0";

    /**
     * Proxy prot number
     */
    private Integer port = 8080;

    /**
     * Read timeout
     */
    private Integer readTimeout = 5000;

    /**
     * Connect timeout
     */
    private Integer connectTimeout = 3000;

    /**
     * Coding format
     */
    private HttpTools.CharsetFormat charset;
}
