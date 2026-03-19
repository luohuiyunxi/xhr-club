package com.xiaohaoren.subject.application.convert;

import com.xiaohaoren.subject.application.dto.SubjectLikedDTO;
import com.xiaohaoren.subject.domain.entity.SubjectLikedBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 题目点赞表 dto转换器
 *
 * @author xiaohaoren
 * @since 2026-03-15 06:23:23
 */
@Mapper
public interface SubjectLikedDTOConverter {

    SubjectLikedDTOConverter INSTANCE = Mappers.getMapper(SubjectLikedDTOConverter.class);

    SubjectLikedBO convertDTOToBO(SubjectLikedDTO subjectLikedDTO);

}
