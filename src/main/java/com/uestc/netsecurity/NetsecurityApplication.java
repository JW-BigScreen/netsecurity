package com.uestc.netsecurity;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

//@ComponentScan({"com.uestc.netsecurity.config", "com.uestc.netsecurity.components","com.uestc.netsecurity.service.serviceImpl"})
@SpringBootApplication
@MapperScan("com.uestc.netsecurity.mapper")
public class NetsecurityApplication extends SpringBootServletInitializer {


    public static void main(String[] args) {

        SpringApplication.run(NetsecurityApplication.class, args);
    }

    @Override//为了打包springboot项目
    protected SpringApplicationBuilder configure(
            SpringApplicationBuilder builder) {
        return builder.sources(this.getClass());
    }
}
