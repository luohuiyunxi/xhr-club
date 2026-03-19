package com.xiaohaoren.subject.application.convert;

import com.xiaohaoren.subject.application.dto.SubjectAnswerDTO;
import com.xiaohaoren.subject.domain.entity.SubjectAnswerBO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-03-20T03:14:07+0800",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_452 (Amazon.com Inc.)"
)
public class SubjectAnswerDTOConverterImpl implements SubjectAnswerDTOConverter {

    @Override
    public SubjectAnswerBO convert(SubjectAnswerDTO subjectAnswerDTO) {
        if ( subjectAnswerDTO == null ) {
            return null;
        }

        SubjectAnswerBO subjectAnswerBO = new SubjectAnswerBO();

        subjectAnswerBO.setOptionType( subjectAnswerDTO.getOptionType() );
        subjectAnswerBO.setOptionContent( subjectAnswerDTO.getOptionContent() );
        subjectAnswerBO.setIsCorrect( subjectAnswerDTO.getIsCorrect() );

        return subjectAnswerBO;
    }

    @Override
    public List<SubjectAnswerBO> convert(List<SubjectAnswerDTO> subjectAnswerDTO) {
        if ( subjectAnswerDTO == null ) {
            return null;
        }

        List<SubjectAnswerBO> list = new ArrayList<SubjectAnswerBO>( subjectAnswerDTO.size() );
        for ( SubjectAnswerDTO subjectAnswerDTO1 : subjectAnswerDTO ) {
            list.add( convert( subjectAnswerDTO1 ) );
        }

        return list;
    }
}
