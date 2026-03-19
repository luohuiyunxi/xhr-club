package com.xiaohaoren.subject.domain.service;

import com.xiaohaoren.subject.common.entity.PageResult;
import com.xiaohaoren.subject.domain.entity.SubjectInfoBO;
import com.xiaohaoren.subject.infra.basic.entity.SubjectInfoEs;

import java.util.List;

/**
 * 描述: 题目领域服务
 *
 * @author xiaohaoren
 */

public interface SubjectInfoDomainService {
    /**
     * 获取题目列表
     *
     * @param subjectInfoBO
     * @return
     */
    PageResult<SubjectInfoBO> getSubjectPage(SubjectInfoBO subjectInfoBO);

    /**
     * 添加题目标签
     *
     * @param subjectInfoBO
     * @return
     */
    void add(SubjectInfoBO subjectInfoBO);

    SubjectInfoBO querySubjectInfo(SubjectInfoBO subjectInfoBO);

    PageResult<SubjectInfoEs> getSubjectPageBySearch(SubjectInfoBO subjectInfoBO);

    List<SubjectInfoBO> getContributeList();
}
