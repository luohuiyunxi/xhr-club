package com.xiaohaoren.subject.domain.handler.subject;

import com.xiaohaoren.subject.common.enums.IsDeletedFlagEnum;
import com.xiaohaoren.subject.common.enums.SubjectInfoTypeEnum;
import com.xiaohaoren.subject.domain.convert.SubjectJudgeConverter;
import com.xiaohaoren.subject.domain.entity.SubjectAnswerBO;
import com.xiaohaoren.subject.domain.entity.SubjectInfoBO;
import com.xiaohaoren.subject.domain.entity.SubjectOptionBO;
import com.xiaohaoren.subject.infra.basic.entity.SubjectJudge;
import com.xiaohaoren.subject.infra.basic.service.SubjectJudgeService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * 描述: 判断题处理器
 *
 * @author xiaohaoren
 */
@Component
public class JudgeTypeHandler implements SubjectTypeHandler{
    @Resource
    private SubjectJudgeService subjectJudgeService;
    @Override
    public SubjectInfoTypeEnum getHandlerType() {
        return SubjectInfoTypeEnum.JUDGE;
    }

    @Override
    public void add(SubjectInfoBO subjectInfoBO) {
        SubjectJudge subjectJudge = new SubjectJudge();
        SubjectAnswerBO subjectAnswerBO = subjectInfoBO.getOptionList().get(0);
        subjectJudge.setSubjectId(subjectInfoBO.getId());
        subjectJudge.setIsCorrect(subjectAnswerBO.getIsCorrect());
        subjectJudge.setIsDeleted(IsDeletedFlagEnum.NOT_DELETED.getCode());
        subjectJudgeService.insert(subjectJudge);
    }
    @Override
    public SubjectOptionBO query(int subjectId) {
        SubjectJudge subjectJudge = new SubjectJudge();
        subjectJudge.setSubjectId(Long.valueOf(subjectId));
        List<SubjectJudge> result = subjectJudgeService.queryByCondition(subjectJudge);
        List<SubjectAnswerBO> subjectAnswerBOList = SubjectJudgeConverter.INSTANCE.convertEntityToBoList(result);
        SubjectOptionBO subjectOptionBO = new SubjectOptionBO();
        subjectOptionBO.setOptionList(subjectAnswerBOList);
        return subjectOptionBO;
    }
}
