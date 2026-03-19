package com.xiaohaoren.auth;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * 描述:权限微服务启动类
 *
 * @author xiaohaoren
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.xiaohaoren")
@MapperScan(basePackages = "com.xiaohaoren.**.mapper")
public class AuthApplication {
    public static void main(String[] args) {
        org.springframework.boot.SpringApplication.run(AuthApplication.class, args);
    }
}
