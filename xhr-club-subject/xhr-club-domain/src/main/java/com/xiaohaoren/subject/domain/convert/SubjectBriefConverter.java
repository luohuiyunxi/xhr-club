package com.xiaohaoren.subject.domain.convert;

import com.xiaohaoren.subject.domain.entity.SubjectAnswerBO;
import com.xiaohaoren.subject.domain.entity.SubjectInfoBO;
import com.xiaohaoren.subject.infra.basic.entity.SubjectBrief;
import com.xiaohaoren.subject.infra.basic.entity.SubjectMultiple;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SubjectBriefConverter {
    SubjectBriefConverter INSTANCE =Mappers.getMapper(SubjectBriefConverter.class);
    SubjectBrief convert(SubjectInfoBO subjectInfoBO);
}
