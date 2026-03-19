package com.xiaohaoren.subject.domain.convert;

import com.xiaohaoren.subject.domain.entity.SubjectCategoryBO;
import com.xiaohaoren.subject.domain.entity.SubjectInfoBO;
import com.xiaohaoren.subject.domain.entity.SubjectOptionBO;
import com.xiaohaoren.subject.infra.basic.entity.SubjectCategory;
import com.xiaohaoren.subject.infra.basic.entity.SubjectInfo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SubjectInfoConverter {
    SubjectInfoConverter INSTANCE =Mappers.getMapper(SubjectInfoConverter.class);

    SubjectInfo convert(SubjectInfoBO subjectInfoBO);
    List<SubjectInfoBO> convert(List<SubjectInfo> subjectInfoList);

    SubjectInfoBO convert(SubjectInfo subjectInfo);

    SubjectInfoBO convertOptionAndInfoToBo(SubjectOptionBO optionBO, SubjectInfo subjectInfo);
}
