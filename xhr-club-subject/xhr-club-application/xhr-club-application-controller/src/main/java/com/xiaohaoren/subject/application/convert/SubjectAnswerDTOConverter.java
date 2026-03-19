package com.xiaohaoren.subject.application.convert;

import com.xiaohaoren.subject.application.dto.SubjectAnswerDTO;
import com.xiaohaoren.subject.application.dto.SubjectInfoDTO;
import com.xiaohaoren.subject.domain.entity.SubjectAnswerBO;
import com.xiaohaoren.subject.domain.entity.SubjectInfoBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SubjectAnswerDTOConverter {
    SubjectAnswerDTOConverter INSTANCE =Mappers.getMapper(SubjectAnswerDTOConverter.class);
    SubjectAnswerBO convert(SubjectAnswerDTO subjectAnswerDTO);
    List<SubjectAnswerBO> convert(List<SubjectAnswerDTO> subjectAnswerDTO);
}
