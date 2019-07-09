package com.stj.config.swagger;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.ApiSelectorBuilder;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
            ApiSelectorBuilder builder = docket
                    .enable(swaggerApiInfo.getEnable())
                    .apiInfo(getApiInfo(swaggerApiInfo))
                    .select();
            String[] basePackages = swaggerApiInfo.getBasePackage();
            if (basePackages != null && basePackages.length > 0) {
                Predicate[] predicates = new Predicate[basePackages.length];
                List<Predicate> predicateList = Arrays.stream(basePackages)
                        .map(basePackage -> (Predicate)RequestHandlerSelectors.basePackage(basePackage))
                        .collect(Collectors.toList());
                builder.apis(Predicates.or(predicateList.toArray(predicates)));
            }
            builder.paths(PathSelectors.any()).build();
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
