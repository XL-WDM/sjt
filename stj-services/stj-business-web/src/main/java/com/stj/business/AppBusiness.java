package com.stj.business;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author: yilan.hu
 * @data: 2019/7/1
 */
@SpringBootApplication
@ComponentScan("com.stj")
@EnableSwagger2
public class AppBusiness {
    public static void main(String[] args){
        SpringApplication.run(AppBusiness.class, args);
    }
}
