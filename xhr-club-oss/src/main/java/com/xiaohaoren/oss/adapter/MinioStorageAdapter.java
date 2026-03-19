package com.xiaohaoren.oss.adapter;

import com.xiaohaoren.oss.entity.FileInfo;
import com.xiaohaoren.oss.util.MinioUtil;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.InputStream;
import java.util.List;

public class MinioStorageAdapter implements StorageAdapter {
    @Resource
    private MinioUtil minioUtil;
    /**
     * minioUrl
     */
    @Value("${minio.url}")
    private String url;
    @Override
    @SneakyThrows
    public void createBucket(String bucketName) {
        minioUtil.createBucket(bucketName);

    }

    @Override
    @SneakyThrows
    public void uploadFile(String bucketName, String objectName, MultipartFile uploadFile) {
        minioUtil.createBucket(bucketName);
        if (objectName != null) {
            minioUtil.uploadFile(uploadFile.getInputStream(), bucketName, objectName + "/" + uploadFile.getOriginalFilename());
        } else {
            minioUtil.uploadFile(uploadFile.getInputStream(), bucketName, uploadFile.getOriginalFilename());
        }


    }

    @Override
    @SneakyThrows
    public List<String> listBuckets() {
        return minioUtil.listBuckets();
    }

    @Override
    @SneakyThrows
    public List<FileInfo> listObjects(String bucketName) {
        return minioUtil.listObjects(bucketName);
    }

    @Override
    @SneakyThrows
    public void deleteObject(String bucketName, String objectName) {
        minioUtil.deleteObject(bucketName, objectName);

    }

    @Override
    @SneakyThrows
    public InputStream downloadObject(String bucketName, String objectName) {
        return minioUtil.downloadObject(bucketName, objectName);
    }

    @Override
    @SneakyThrows
    public String getUrl(String bucket, String objectName) {
        return url + "/" + bucket + "/" + objectName;
    }


}
