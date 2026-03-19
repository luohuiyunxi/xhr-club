package com.xiaohaoren.subject.domain.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 题目标签表BO
 *
 * @author makejava
 * @since 2025-09-28 01:26:52
 */
@Data
public class SubjectLabelBO implements Serializable {
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

