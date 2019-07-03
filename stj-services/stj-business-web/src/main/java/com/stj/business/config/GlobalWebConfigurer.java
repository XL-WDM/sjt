package com.stj.business.config;

import com.stj.common.base.constant.BaseConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.config.annotation.CorsRegistration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author: yilan.hu
 * @data: 2019/5/30
 */
@Component
public class GlobalWebConfigurer implements WebMvcConfigurer {

    @Autowired(required = false)
    private Cross cross;

    /**
     * 跨域
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        if (cross != null) {
            CorsRegistration corsRegistration = registry.addMapping("/**");
            if (!StringUtils.isEmpty(cross.getOrigin())) {
                corsRegistration.allowedOrigins(cross.getOrigin())
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
