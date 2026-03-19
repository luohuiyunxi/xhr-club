package com.xiaohaoren.oss.util;

import com.xiaohaoren.oss.entity.FileInfo;
import io.minio.*;
import io.minio.messages.Bucket;
import io.minio.messages.Item;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Minio文件操作工具
 * @Author: xhr
 * @Date: 2020/3/27 10:07
 */
@Component
public class MinioUtil {

    @Resource
    private MinioClient minioClient;


    /**
     * 创建bucket
     */
    public void createBucket(String bucketName) throws Exception{
        boolean exists = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
        if(!exists){
            minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
        }
    }
    /**
     * 上传文件
     */
    public void uploadFile(InputStream inputStream, String bucket, String objectName) throws Exception {

        String contentType = getContentType(objectName);

        minioClient.putObject(
                PutObjectArgs.builder()
                        .bucket(bucket)
                        .object(objectName)
                        .stream(inputStream, -1, 5242880)
                        .contentType(contentType)
                        .build()
        );
    }
    private String getContentType(String fileName) {
        String suffix = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
        switch (suffix) {
            case "jpg":
            case "jpeg":
                return "image/jpeg";
            case "png":
                return "image/png";
            case "gif":
                return "image/gif";
            case "pdf":
                return "application/pdf";
            case "txt":
                return "text/plain";
            case "mp4":
                return "video/mp4";
            default:
                return "application/octet-stream";
        }
    }

    /**
     * 列出所有桶
     */
    public List<String> listBuckets() throws Exception{
        List<Bucket> buckets = minioClient.listBuckets();
        return buckets.stream().map(Bucket::name).collect(Collectors.toList());
    }
    /**
     * 列出指定桶下的所有文件
     */
    public List<FileInfo> listObjects(String bucketName) throws Exception{
        Iterable<Result<Item>> results = minioClient.listObjects(ListObjectsArgs.builder().bucket(bucketName).build());
        List<FileInfo> fileInfos = new LinkedList<>();
        for (Result<Item> result : results) {
            Item item = result.get();
            FileInfo fileInfo = new FileInfo();
            fileInfo.setFilename(item.objectName());
            fileInfo.setDirectoryFlag(item.isDir());
            fileInfo.setEtag(item.etag());
            fileInfos.add(fileInfo);
        }
        return fileInfos;
    }
    /**
     * 删除文件
     */
    public void deleteObject(String bucketName, String objectName) throws Exception{
        minioClient.removeObject(RemoveObjectArgs.builder().bucket(bucketName).object(objectName).build());
    }
    /**
     * 下载文件
     */
    public InputStream downloadObject(String bucketName, String objectName) throws Exception{
        return minioClient.getObject(GetObjectArgs.builder().bucket(bucketName).object(objectName).build());
    }

}
