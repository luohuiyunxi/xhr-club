package com.xiaohaoren.oss.adapter;

import com.xiaohaoren.oss.entity.FileInfo;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;


public interface StorageAdapter {
    /**
     * 创建bucket
     */
    void createBucket(String bucketName);
    /**
     * 上传文件
     */
    void uploadFile(String bucketName, String ObjectName, MultipartFile uploadFile);

    /**
     * 列出所有桶
     */
    List<String> listBuckets();
    /**
     * 列出指定桶下的所有文件
     */
    List<FileInfo> listObjects(String bucketName);
    /**
     * 删除文件
     */
    void deleteObject(String bucketName, String objectName);
    /**
     * 下载文件
     */
    public InputStream downloadObject(String bucketName, String objectName);

    String getUrl(String bucket, String objectName);
}
