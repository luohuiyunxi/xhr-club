package com.xiaohaoren.subject.application.controller;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Preconditions;
import com.xiaohaoren.subject.application.convert.SubjectCatrgoryDTOConverter;
import com.xiaohaoren.subject.application.convert.SubjectLableDTOConverter;
import com.xiaohaoren.subject.application.dto.SubjectCategoryDTO;
import com.xiaohaoren.subject.application.dto.SubjectLabelDTO;
import com.xiaohaoren.subject.common.entity.Result;
import com.xiaohaoren.subject.domain.entity.SubjectCategoryBO;
import com.xiaohaoren.subject.domain.entity.SubjectLabelBO;
import com.xiaohaoren.subject.domain.service.SubjectLableDomainService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 描述:标签控制器
 *
 * @author XiaoHaoRen
 * @date 2019-03-09 14:04
 */
@RestController
@RequestMapping("/subject/label")
@Slf4j
public class SubjectLableController {
    @Resource
    private SubjectLableDomainService  subjectLableDomainService;
    @PostMapping("/add")
    public Result<Boolean> add(@RequestBody SubjectLabelDTO subjectLabelDTO) {
        try {
            if(log.isInfoEnabled()){
                log.info("添加分类信息：{}", JSON.toJSONString(subjectLabelDTO));
            }
            Preconditions.checkArgument(StringUtils.isNotBlank(subjectLabelDTO.getLabelName()), "分类名称不能为空");
            SubjectLabelBO subjectLabelBO = SubjectLableDTOConverter.INSTANCE.convert(subjectLabelDTO);
            Boolean result = subjectLableDomainService.add(subjectLabelBO);
            return Result.ok(result);
        } catch (Exception e) {
            log.error("添加分类信息失败：{}", e.getMessage());
            return Result.fail(false);
        }
    }
    @PostMapping("/update")
    public Result<Boolean> update(@RequestBody SubjectLabelDTO subjectLabelDTO) {
        try {
            if(log.isInfoEnabled()){
                log.info("修改分类信息：{}", JSON.toJSONString(subjectLabelDTO));
            }
            Preconditions.checkNotNull(subjectLabelDTO.getId(), "标签id不能为空");
            SubjectLabelBO subjectLabelBO = SubjectLableDTOConverter.INSTANCE.convert(subjectLabelDTO);
            Boolean result = subjectLableDomainService.update(subjectLabelBO);
            return Result.ok(result);
        } catch (Exception e) {
            log.error("修改分类信息失败：{}", e.getMessage());
            return Result.fail(false);
        }
    }
    @PostMapping("/delete")
    public Result<Boolean> delete(@RequestBody SubjectLabelDTO subjectLabelDTO) {
        try {
            if(log.isInfoEnabled()){
                log.info("删除分类信息：{}", JSON.toJSONString(subjectLabelDTO));
            }
            Preconditions.checkNotNull(subjectLabelDTO.getId(), "标签id不能为空");
            SubjectLabelBO subjectLabelBO = SubjectLableDTOConverter.INSTANCE.convert(subjectLabelDTO);
            Boolean result = subjectLableDomainService.delete(subjectLabelBO);
            return Result.ok(result);
        } catch (Exception e) {
            log.error("删除分类信息失败：{}", e.getMessage());
            return Result.fail(false);
        }
    }
    @PostMapping("/queryLabelByCategoryId")
    public Result<List<SubjectLabelDTO>> queryLabelByCategoryId(@RequestBody SubjectLabelDTO subjectLabelDTO) {
        try {
            if(log.isInfoEnabled()){
                log.info("查询分类信息：{}", JSON.toJSONString(subjectLabelDTO));
            }
            Preconditions.checkNotNull(subjectLabelDTO.getCategoryId(), "分类id不能为空");
            SubjectLabelBO subjectLabelBO = SubjectLableDTOConverter.INSTANCE.convert(subjectLabelDTO);
            List<SubjectLabelBO> subjectLabelBOList = subjectLableDomainService.queryLabelByCategoryId(subjectLabelBO);
            List<SubjectLabelDTO> subjectLabelDTOList = SubjectLableDTOConverter.INSTANCE.convert(subjectLabelBOList);
            return Result.ok(subjectLabelDTOList);
        }
        catch (Exception e) {
            log.error("查询分类信息失败：{}", e.getMessage());
            return Result.fail("查询失败");
        }
    }

}
