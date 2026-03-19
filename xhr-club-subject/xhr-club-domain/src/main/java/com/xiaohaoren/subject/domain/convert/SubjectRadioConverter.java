package com.xiaohaoren.subject.domain.convert;

import com.xiaohaoren.subject.domain.entity.SubjectAnswerBO;
import com.xiaohaoren.subject.domain.entity.SubjectInfoBO;
import com.xiaohaoren.subject.infra.basic.entity.SubjectInfo;
import com.xiaohaoren.subject.infra.basic.entity.SubjectRadio;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SubjectRadioConverter {
    SubjectRadioConverter INSTANCE =Mappers.getMapper(SubjectRadioConverter.class);

    SubjectRadio convert(SubjectAnswerBO subjectAnswerBO);
    List <SubjectRadio> convert(List<SubjectAnswerBO> subjectAnswerBOList);

    List<SubjectAnswerBO> convertEntityToBoList(List<SubjectRadio> result);
}
