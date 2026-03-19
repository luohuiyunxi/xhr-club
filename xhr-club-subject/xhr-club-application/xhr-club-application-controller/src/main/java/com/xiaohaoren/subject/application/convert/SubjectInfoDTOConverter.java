package com.xiaohaoren.subject.application.convert;

import com.xiaohaoren.subject.application.dto.SubjectCategoryDTO;
import com.xiaohaoren.subject.application.dto.SubjectInfoDTO;
import com.xiaohaoren.subject.domain.entity.SubjectCategoryBO;
import com.xiaohaoren.subject.domain.entity.SubjectInfoBO;
import com.xiaohaoren.subject.infra.basic.entity.SubjectInfo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SubjectInfoDTOConverter {
    SubjectInfoDTOConverter INSTANCE =Mappers.getMapper(SubjectInfoDTOConverter.class);
    SubjectInfoBO convert(SubjectInfoDTO subjectInfoDTO);
    SubjectInfoDTO convert(SubjectInfoBO subjectInfoBO);
    List<SubjectInfoDTO> convert(List<SubjectInfoBO> subjectInfoBOList);
}
