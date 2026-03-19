package com.xiaohaoren.subject.domain.handler.subject;

import com.xiaohaoren.subject.common.enums.SubjectInfoTypeEnum;
import com.xiaohaoren.subject.domain.entity.SubjectInfoBO;
import com.xiaohaoren.subject.domain.entity.SubjectOptionBO;

public interface SubjectTypeHandler {
    /**
     * 获取处理器类型
     */
    SubjectInfoTypeEnum getHandlerType();
    /**
     * 添加
     */
    void add(SubjectInfoBO subjectInfoBO);

    /**
     * 实际的题目的查询
     */
    SubjectOptionBO query(int subjectId);

}
