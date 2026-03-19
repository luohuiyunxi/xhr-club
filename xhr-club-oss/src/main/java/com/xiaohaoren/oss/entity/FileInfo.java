package com.xiaohaoren.oss.entity;

import lombok.Data;

/**
 * 文件信息
 */
@Data
public class FileInfo {
    private String filename;

    private Boolean directoryFlag;

    private String etag;

}
