package com.xiaohaoren.oss.config;


import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * minio 配置类
 */
@Configuration
public class MinioConfig {
    /**
     * 连接地址
     */
    @Value("${minio.url}")
    private String url;
    /**
     * 账号
     */
    @Value("${minio.accessKey}")
    private String accessKey;
    /**
     * 密码
     */
    @Value("${minio.secretKey}")
    private String secretKey;
    /**
     * 获取MinioClient
     *
     * @return
     */
    @Bean
    public MinioClient getMinioClient() {
        return MinioClient.builder()
                .endpoint(url)
                .credentials(accessKey, secretKey)
                .build();
    }

}
