package com.xiaohaoren.subject.application.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 题目标签表DTAO
 *
 * @author makejava
 * @since 2025-09-28 01:26:52
 */
@Data
public class SubjectLabelDTO implements Serializable {
    private static final long serialVersionUID = 201667487089516398L;
/**
     * 主键
     */
    private Long id;
/**
     * 标签分类
     */
    private String labelName;
/**
     * 排序
     */
    private Integer sortNum;
    /**
     * 分类id
     */
    private Long categoryId;

    private Integer isDeleted;




}

