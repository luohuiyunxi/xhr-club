package com.xiaohaoren.oss.controller;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.xiaohaoren.oss.entity.Result;
import com.xiaohaoren.oss.service.FileService;
import com.xiaohaoren.oss.util.MinioUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RefreshScope
@Slf4j
public class FileController {

    @Resource
    private FileService fileService;
    @Value("${storage.service.type}")
    private  String storageType;

    @RequestMapping("/list")
    public List<String> list() throws Exception {
        return fileService.listBuckets();
    }
    @RequestMapping("/testNacos")
    public String testNacos() throws Exception {
        return storageType;
    }
    /**
     * 上传文件
     */
    @RequestMapping("/upload")
    public Result upload(MultipartFile uploadFile, String bucket, String objectName) throws Exception {
        log.info("bucket:{},objectName:{}", bucket, objectName);
        String url = fileService.uploadFile(uploadFile, bucket, objectName);
        log.info("url:{}", url);
        return Result.ok(url);
    }
}
