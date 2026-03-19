package com.xiaohaoren.subject.domain.handler.subject;

import com.google.common.base.Preconditions;
import com.xiaohaoren.subject.common.enums.IsDeletedFlagEnum;
import com.xiaohaoren.subject.common.enums.SubjectInfoTypeEnum;
import com.xiaohaoren.subject.domain.convert.SubjectRadioConverter;
import com.xiaohaoren.subject.domain.entity.SubjectAnswerBO;
import com.xiaohaoren.subject.domain.entity.SubjectInfoBO;
import com.xiaohaoren.subject.domain.entity.SubjectOptionBO;
import com.xiaohaoren.subject.infra.basic.entity.SubjectRadio;
import com.xiaohaoren.subject.infra.basic.service.SubjectRadioService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;

/**
 * 描述: 单选题处理类
 *
 * @author xiaohaoren
 */
@Component
public class RadioTypeHandler implements SubjectTypeHandler{
    @Resource
    private SubjectRadioService subjectRadioService;
    @Override
    public SubjectInfoTypeEnum getHandlerType() {
        return SubjectInfoTypeEnum.RADIO;
    }

    @Override
    public void add(SubjectInfoBO subjectInfoBO) {
        List<SubjectRadio> subjectRadioList = new LinkedList<>();
        Preconditions.checkNotNull(subjectInfoBO.getOptionList(), "选项不能为空");
        subjectInfoBO.getOptionList().forEach(subjectAnswerBO -> {
            SubjectRadio subjectRadio = SubjectRadioConverter.INSTANCE.convert(subjectAnswerBO);
            subjectRadio.setSubjectId(subjectInfoBO.getId());
            subjectRadio.setIsDeleted(IsDeletedFlagEnum.NOT_DELETED.getCode());
            subjectRadioList.add(subjectRadio);
        });
        subjectRadioService.batchInsert(subjectRadioList);
    }
    @Override
    public SubjectOptionBO query(int subjectId) {
        SubjectRadio subjectRadio = new SubjectRadio();
        subjectRadio.setSubjectId(Long.valueOf(subjectId));
        List<SubjectRadio> result = subjectRadioService.queryByCondition(subjectRadio);
        List<SubjectAnswerBO> subjectAnswerBOList = SubjectRadioConverter.INSTANCE.convertEntityToBoList(result);
        SubjectOptionBO subjectOptionBO = new SubjectOptionBO();
        subjectOptionBO.setOptionList(subjectAnswerBOList);
        return subjectOptionBO;
    }

}
