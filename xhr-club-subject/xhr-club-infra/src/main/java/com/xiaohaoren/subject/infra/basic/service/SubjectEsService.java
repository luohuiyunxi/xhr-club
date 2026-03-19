package com.xiaohaoren.subject.infra.basic.service;

import com.xiaohaoren.subject.common.entity.PageResult;
import com.xiaohaoren.subject.infra.basic.entity.SubjectInfoEs;

public interface SubjectEsService {
    /**
     * 插入数据
     *
     * @param subjectInfoEs
     * @return
     */
    boolean insert(SubjectInfoEs subjectInfoEs);
    /**
     * 查询数据
     *
     * @return
     */
    PageResult<SubjectInfoEs> querySubjectList(SubjectInfoEs subjectInfoEs);

}
