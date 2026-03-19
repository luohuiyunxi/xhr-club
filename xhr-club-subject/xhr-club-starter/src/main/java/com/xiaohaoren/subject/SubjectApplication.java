package com.xiaohaoren.subject;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * 描述:刷题微服务启动类
 *
 * @author xiaohaoren
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.xiaohaoren")
@MapperScan(basePackages = "com.xiaohaoren.**.mapper")
@EnableFeignClients(basePackages = "com.xiaohaoren")
public class SubjectApplication {
    public static void main(String[] args) {
        org.springframework.boot.SpringApplication.run(SubjectApplication.class, args);
    }
}
