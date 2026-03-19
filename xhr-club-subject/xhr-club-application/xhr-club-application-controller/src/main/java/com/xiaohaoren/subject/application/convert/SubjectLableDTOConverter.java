package com.xiaohaoren.subject.application.convert;

import com.xiaohaoren.subject.application.dto.SubjectCategoryDTO;
import com.xiaohaoren.subject.application.dto.SubjectLabelDTO;
import com.xiaohaoren.subject.domain.entity.SubjectCategoryBO;
import com.xiaohaoren.subject.domain.entity.SubjectLabelBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;
/**
 * 类功能描述:标签dto转化
 *
 * @author XiaoHaoRen
 * @version 1.0.0
 * @date 2019-05-09 17:04
 */
@Mapper
public interface SubjectLableDTOConverter {
    SubjectLableDTOConverter INSTANCE = Mappers.getMapper(SubjectLableDTOConverter.class);
    SubjectLabelBO convert(SubjectLabelDTO subjectLabelDTO);
    List<SubjectLabelDTO> convert(List<SubjectLabelBO> subjectLabelDTOList);

}
