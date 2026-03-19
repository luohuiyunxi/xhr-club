package com.xiaohaoren.subject.domain.service;

import com.xiaohaoren.subject.domain.entity.SubjectCategoryBO;
import com.xiaohaoren.subject.infra.basic.entity.SubjectCategory;
import org.springframework.stereotype.Service;

import java.util.List;


public interface SubjectCategoryDomainService {
    void add(SubjectCategoryBO subjectCategoryBO);

    List<SubjectCategoryBO> queryPrimaryCategory(SubjectCategoryBO subjectCategoryBO);

    List<SubjectCategoryBO> queryCategory(SubjectCategoryBO subjectCategoryBO);

    Boolean update(SubjectCategoryBO subjectCategoryBO);

    Boolean delete(SubjectCategoryBO subjectCategoryBO);

    List<SubjectCategoryBO> queryCategoryAndLabel(SubjectCategoryBO subjectCategoryBO);
}
