package com.xiaohaoren.subject.domain.convert;

import com.xiaohaoren.subject.domain.entity.SubjectCategoryBO;
import com.xiaohaoren.subject.infra.basic.entity.SubjectCategory;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SubjectCatrgoryConverter {
    SubjectCatrgoryConverter INSTANCE =Mappers.getMapper(SubjectCatrgoryConverter.class);

    SubjectCategory convert(SubjectCategoryBO subjectCategoryBO);
    List<SubjectCategoryBO> convert(List<SubjectCategory> subjectCategoryList);
}
