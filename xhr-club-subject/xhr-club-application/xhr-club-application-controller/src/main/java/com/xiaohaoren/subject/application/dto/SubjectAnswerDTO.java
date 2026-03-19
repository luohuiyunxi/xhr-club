package com.xiaohaoren.subject.application.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 题目答案DTO
 *
 * @author makejava
 * @since 2025-10-04 23:15:05
 */
@Data
public class SubjectAnswerDTO implements Serializable {
    private static final long serialVersionUID = 800980640792518954L;
    /**
     * 答案选项标识
     */
    private Integer optionType;
    /**
     * 答案
     */
    private String optionContent;
    /**
     * 是否正确
     */
    private Integer isCorrect;




}

