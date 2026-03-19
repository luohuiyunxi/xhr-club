package com.xiaohaoren.subject.domain.service.Impl;

import com.alibaba.fastjson.JSON;
import com.xiaohaoren.subject.common.enums.CategoryTypeEnum;
import com.xiaohaoren.subject.common.enums.IsDeletedFlagEnum;
import com.xiaohaoren.subject.domain.convert.SubjectLableConverter;
import com.xiaohaoren.subject.domain.entity.SubjectLabelBO;
import com.xiaohaoren.subject.domain.service.SubjectLableDomainService;
import com.xiaohaoren.subject.infra.basic.entity.SubjectCategory;
import com.xiaohaoren.subject.infra.basic.entity.SubjectLabel;
import com.xiaohaoren.subject.infra.basic.entity.SubjectMapping;
import com.xiaohaoren.subject.infra.basic.mapper.SubjectMappingDao;
import com.xiaohaoren.subject.infra.basic.service.SubjectCategoryService;
import com.xiaohaoren.subject.infra.basic.service.SubjectLabelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class SubjectLableDomainServiceImpl implements SubjectLableDomainService {
    @Resource
    private SubjectLabelService subjectLabelService;
    @Resource
    private SubjectMappingDao subjectMappingDao;
    @Resource
    private SubjectCategoryService subjectCategoryService;
    @Override
    public Boolean add(SubjectLabelBO subjectLabelBO) {
        if(log.isInfoEnabled()){
            log.info("添加分类信息：{}", JSON.toJSONString(subjectLabelBO));
        }
        SubjectLabel subjectLabel = SubjectLableConverter.INSTANCE.convert(subjectLabelBO);
        int insert = subjectLabelService.insert(subjectLabel);
        return insert > 0;
    }

    @Override
    public Boolean update(SubjectLabelBO subjectLabelBO) {
        if (log.isInfoEnabled()){
            log.info("修改分类信息：{}", JSON.toJSONString(subjectLabelBO));
        }
        SubjectLabel subjectLabel = SubjectLableConverter.INSTANCE.convert(subjectLabelBO);
        int update = subjectLabelService.update(subjectLabel);
        return update > 0;
    }

    @Override
    public Boolean delete(SubjectLabelBO subjectLabelBO) {
        if (log.isInfoEnabled()){
            log.info("删除分类信息：{}", JSON.toJSONString(subjectLabelBO));
        }
        SubjectLabel subjectLabel = SubjectLableConverter.INSTANCE.convert(subjectLabelBO);
        subjectLabel.setIsDeleted(IsDeletedFlagEnum.DELETED.getCode());
        int delete = subjectLabelService.update(subjectLabel);
        return delete > 0;
    }

    @Override
    public List<SubjectLabelBO> queryLabelByCategoryId(SubjectLabelBO subjectLabelBO) {
        if (log.isInfoEnabled()){
            log.info("查询分类信息：{}", JSON.toJSONString(subjectLabelBO));
        }
        SubjectCategory subjectCategory = subjectCategoryService.queryById(subjectLabelBO.getCategoryId());
        if(CategoryTypeEnum.PRIMARY.getCode() == subjectCategory.getCategoryType()){
            SubjectLabel subjectLabel = new SubjectLabel();
            subjectLabel.setCategoryId(subjectCategory.getId().toString());
            List<SubjectLabel> subjectLabels = subjectLabelService.queryByCondition(subjectLabel);
            List<SubjectLabelBO> subjectLabelBOList = SubjectLableConverter.INSTANCE.convert(subjectLabels);
            return subjectLabelBOList;
        }
        Long categoryId = subjectLabelBO.getCategoryId();
        SubjectMapping subjectMapping = new SubjectMapping();
        subjectMapping.setCategoryId(categoryId);
        subjectMapping.setIsDeleted(IsDeletedFlagEnum.NOT_DELETED.getCode());
        List<SubjectMapping> subjectMappingList = subjectMappingDao.queryLabelId(subjectMapping);
        if (subjectMappingList == null || subjectMappingList.isEmpty()){
            return Collections.emptyList();
        }
        List<Long> collect = subjectMappingList.stream().map(subjectMapping1 -> subjectMapping1.getLabelId()).collect(Collectors.toList());
        List<SubjectLabel> subjectLabels = subjectLabelService.queryByIds(collect);
        List<SubjectLabelBO> subjectLabelBOList = SubjectLableConverter.INSTANCE.convert(subjectLabels);
        return subjectLabelBOList;
    }
}
