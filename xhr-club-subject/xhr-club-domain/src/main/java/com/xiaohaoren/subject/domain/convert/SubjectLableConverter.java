package com.xiaohaoren.subject.domain.convert;

import com.xiaohaoren.subject.domain.entity.SubjectCategoryBO;
import com.xiaohaoren.subject.domain.entity.SubjectLabelBO;
import com.xiaohaoren.subject.infra.basic.entity.SubjectCategory;
import com.xiaohaoren.subject.infra.basic.entity.SubjectLabel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SubjectLableConverter {
    SubjectLableConverter INSTANCE =Mappers.getMapper(SubjectLableConverter.class);
    SubjectLabel convert(SubjectLabelBO subjectLabelBO);
    List<SubjectLabelBO> convert(List<SubjectLabel> subjectLabelBOList);

}
