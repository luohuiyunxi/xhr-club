package com.xiaohaoren.subject.domain.service;

import com.xiaohaoren.subject.domain.entity.SubjectCategoryBO;
import com.xiaohaoren.subject.domain.entity.SubjectLabelBO;

import java.util.List;

/**
 * 描述: 题目标签领域服务
 *
 * @author xiaohaoren
 */

public interface SubjectLableDomainService {
    /**
     * 添加题目标签
     *
     * @param subjectCategoryBO
     * @return
     */
    Boolean add(SubjectLabelBO subjectCategoryBO);
    /**
     * 更新题目标签
     *
     * @param subjectLabelBO
     * @return
     */
    Boolean update(SubjectLabelBO subjectLabelBO);

    /**
     * 删除题目标签
     *
     * @param subjectLabelBO
     * @return
     */
    Boolean delete(SubjectLabelBO subjectLabelBO);
    /**
     * 根据分类id查询题目标签
     *
     * @param subjectLabelBO
     * @return
     */
    List<SubjectLabelBO> queryLabelByCategoryId(SubjectLabelBO subjectLabelBO);
}
