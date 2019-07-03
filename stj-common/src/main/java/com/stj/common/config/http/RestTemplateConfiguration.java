package com.stj.common.config.http;

import com.stj.common.config.http.tools.HttpTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * @author: yilan.hu
 * @data: 2019/7/1
 */
@Configuration
public class RestTemplateConfiguration {

    @Bean
    public RestTemplate restTemplate(@Autowired(required = false) ProxyConfig proxyConfig) {
        proxyConfig = proxyConfig == null ? new ProxyConfig() : proxyConfig;

        SimpleClientHttpRequestFactory httpRequestFactory = null;

        if (!proxyConfig.getSshCheck()) {
            httpRequestFactory = HttpTools.getUnsafeClientHttpRequestFactory();
        }

        if (proxyConfig.getEnableProxy()) {
            if (httpRequestFactory != null) {
                HttpTools.setProxy(httpRequestFactory, proxyConfig);
            } else {
                httpRequestFactory = HttpTools.getProxyClientHttpRequestFactory(proxyConfig);
            }
        }

        RestTemplate restTemplate = httpRequestFactory != null
                ? new RestTemplate(httpRequestFactory)
                : new RestTemplate();

        // 设置编码
        if (proxyConfig.getCharset() != null) {
            HttpTools.setRestTemplateCharset(restTemplate, proxyConfig.getCharset());
        }

        return restTemplate;
    }
}
