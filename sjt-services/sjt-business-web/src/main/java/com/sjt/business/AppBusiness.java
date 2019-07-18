package com.sjt.business;

import com.sjt.common.utils.SnowflakeIdUtils;
import com.sjt.config.mybatis.plus.annotation.EnableMybatisPlusOptimization;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author: yilan.hu
 * @data: 2019/7/1
 */
@SpringBootApplication
@ComponentScan("com.sjt")
@MapperScan({"com.sjt.business.mapper"})
@EnableSwagger2
@EnableMybatisPlusOptimization
public class AppBusiness {
    public static void main(String[] args) {
        SpringApplication.run(AppBusiness.class, args);
    }

    @Bean
    public SnowflakeIdUtils snowflakeIdUtils() {
        return new SnowflakeIdUtils(3, 1);
    }
}
