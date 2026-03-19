package com.xiaohaoren.subject.domain.convert;

import com.xiaohaoren.subject.domain.entity.SubjectAnswerBO;
import com.xiaohaoren.subject.domain.entity.SubjectInfoBO;
import com.xiaohaoren.subject.infra.basic.entity.SubjectInfo;
import com.xiaohaoren.subject.infra.basic.entity.SubjectMultiple;
import com.xiaohaoren.subject.infra.basic.entity.SubjectRadio;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SubjectMultipleConverter {
    SubjectMultipleConverter INSTANCE =Mappers.getMapper(SubjectMultipleConverter.class);

    SubjectMultiple convert(SubjectAnswerBO subjectAnswerBO);

    List<SubjectAnswerBO> convertEntityToBoList(List<SubjectMultiple> result);
}
