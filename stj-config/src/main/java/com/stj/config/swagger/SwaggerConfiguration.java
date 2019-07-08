package com.stj.config.swagger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * swagger配置
 * @author: yilan.hu
 * @data: 2019/7/1
 */
@Configuration
public class SwaggerConfiguration {
    @Bean
    public Docket api(@Autowired(required = false) SwaggerApiInfo swaggerApiInfo) {
        // 默认不开启swagger
        Docket docket = new Docket(DocumentationType.SWAGGER_2).enable(false);
        if (swaggerApiInfo != null) {
            docket.enable(swaggerApiInfo.getEnable())
                  .apiInfo(getApiInfo(swaggerApiInfo))
                  .select()
                  .apis(RequestHandlerSelectors.basePackage(swaggerApiInfo.getBasePackage()))
                  .paths(PathSelectors.any())
                  .build();
        }
        return docket;
    }

    private ApiInfo getApiInfo(SwaggerApiInfo swaggerApiInfo) {
        return new ApiInfoBuilder()
                .title(swaggerApiInfo.getTitle())
                .description(swaggerApiInfo.getDescription())
                .version(swaggerApiInfo.getVersion())
                .license(swaggerApiInfo.getLicense())
                .licenseUrl(swaggerApiInfo.getLicenseUrl())
                .build();
    }
}
