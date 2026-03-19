package com.xiaohaoren.oss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * 描述:oss启动类
 *
 * @author xiaohaoren
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.xiaohaoren")
public class OssApplication {
    public static void main(String[] args) {
        SpringApplication.run(OssApplication.class, args);
    }
}
