package com.sjt.business.web.config;

import com.sjt.business.web.interceptor.UserSignatureInterceptors;
import com.sjt.common.base.constant.BaseConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author: yilan.hu
 * @data: 2019/5/30
 */
@Configuration
public class GlobalWebConfigurer implements WebMvcConfigurer {

    @Autowired(required = false)
    private Cross cross;

    @Autowired
    private UserSignatureInterceptors userSignatureInterceptors;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration oAuth = registry.addInterceptor(userSignatureInterceptors).addPathPatterns("/**");
        if (cross.getApiWhiteList() != null && cross.getApiWhiteList().length > 0) {
            oAuth.excludePathPatterns(Arrays.asList(cross.getApiWhiteList()));
        }
    }

    /**
     * 跨域
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        if (cross != null) {
            CorsRegistration corsRegistration = registry.addMapping("/**");

            String[] origin = cross.getOrigin();
            if (origin != null && origin.length > 0) {
                corsRegistration.allowedOrigins(origin)
                // 允许cookie跨域
                .allowCredentials(true)
                // 预检命令缓存
                .maxAge(BaseConstant.Second.DAY);
            }
            if (cross.getHeders() != null && cross.getHeders().length > 0) {
                corsRegistration.allowedHeaders(cross.getHeders());
            }
            if (cross.getMethods() != null && cross.getMethods().length > 0) {
                corsRegistration.allowedMethods(cross.getMethods());
            }
        }
    }
}
