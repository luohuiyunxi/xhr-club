package com.xiaohaoren.oss.adapter;

import com.xiaohaoren.oss.entity.FileInfo;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class AliStorageAdapter implements StorageAdapter {

    @Override
    public void createBucket(String bucketName) {

    }

    @Override
    public void uploadFile(String bucketName, String ObjectName, MultipartFile uploadFile) {

    }

    @Override
    public List<String> listBuckets() {
        List<String> buckets = new ArrayList<>();
        buckets.add("aliyun");
        return buckets;
    }

    @Override
    public List<FileInfo> listObjects(String bucketName) {
        return null;
    }

    @Override
    public void deleteObject(String bucketName, String objectName) {

    }

    @Override
    public InputStream downloadObject(String bucketName, String objectName) {
        return null;
    }

    @Override
    public String getUrl(String bucket, String objectName) {
        return "";
    }
}
