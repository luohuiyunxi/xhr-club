package com.xiaohaoren.club.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * 描述:网关启动类
 *
 * @author xiaohaoren
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.xiaohaoren")
public class GatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }
}
