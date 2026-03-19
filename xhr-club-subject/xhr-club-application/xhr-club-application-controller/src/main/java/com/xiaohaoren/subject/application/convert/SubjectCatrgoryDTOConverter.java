package com.xiaohaoren.subject.application.convert;

import com.xiaohaoren.subject.application.dto.SubjectCategoryDTO;
import com.xiaohaoren.subject.domain.entity.SubjectCategoryBO;
import com.xiaohaoren.subject.infra.basic.entity.SubjectCategory;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SubjectCatrgoryDTOConverter {
    SubjectCatrgoryDTOConverter INSTANCE =Mappers.getMapper(SubjectCatrgoryDTOConverter.class);

    SubjectCategoryBO convert(SubjectCategoryDTO subjectCategoryDTO);

    List<SubjectCategoryDTO> convert(List<SubjectCategoryBO> subjectCategoryBOList);

    SubjectCategoryDTO convert(SubjectCategoryBO subjectCategoryBO);
}
