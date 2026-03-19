package com.xiaohaoren.oss.service;


import com.xiaohaoren.oss.adapter.StorageAdapter;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class FileService {
    private final StorageAdapter storageAdapter;
    public FileService(StorageAdapter storageAdapter) {
        this.storageAdapter = storageAdapter;
    }
    /**
     * 列出所有桶
     */
    public List<String> listBuckets() {
        return storageAdapter.listBuckets();
    }

    /**
     * 上传文件
     */
    public String uploadFile(MultipartFile uploadFile, String bucket, String objectName){
        storageAdapter.uploadFile(bucket,objectName,uploadFile);
        objectName = objectName + "/" + uploadFile.getOriginalFilename();
        return storageAdapter.getUrl(bucket, objectName);
    }
}
