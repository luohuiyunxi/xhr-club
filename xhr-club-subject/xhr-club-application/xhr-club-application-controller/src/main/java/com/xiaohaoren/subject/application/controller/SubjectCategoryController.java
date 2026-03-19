package com.xiaohaoren.subject.application.controller;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Preconditions;
import com.xiaohaoren.subject.application.convert.SubjectCatrgoryDTOConverter;
import com.xiaohaoren.subject.application.convert.SubjectLableDTOConverter;
import com.xiaohaoren.subject.application.dto.SubjectCategoryDTO;
import com.xiaohaoren.subject.application.dto.SubjectLabelDTO;
import com.xiaohaoren.subject.common.entity.Result;
import com.xiaohaoren.subject.domain.entity.SubjectCategoryBO;
import com.xiaohaoren.subject.domain.service.SubjectCategoryDomainService;
import com.xiaohaoren.subject.infra.basic.entity.SubjectCategory;
import com.xiaohaoren.subject.infra.basic.mapper.SubjectCategoryDao;
import com.xiaohaoren.subject.infra.basic.service.SubjectCategoryService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.mapstruct.ap.shaded.freemarker.template.utility.StringUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("/subject/category")
@Slf4j
public class SubjectCategoryController {
    @Resource
    private SubjectCategoryDomainService subjectCategoryDomainService;
    @PostMapping("/add")
    public Result<Boolean> add(@RequestBody SubjectCategoryDTO subjectCategoryDTO) {
        try {
            if(log.isInfoEnabled()){
                log.info("添加分类信息：{}", subjectCategoryDTO);
            }
            Preconditions.checkNotNull(subjectCategoryDTO.getCategoryType(), "分类类型不能为空");
            Preconditions.checkArgument(!StringUtils.isBlank(subjectCategoryDTO.getCategoryName()), "分类名称不能为空");
            Preconditions.checkNotNull(subjectCategoryDTO.getParentId(), "父级分类不能为空");
            SubjectCategoryBO subjectCategoryBO = SubjectCatrgoryDTOConverter.INSTANCE.convert(subjectCategoryDTO);
            subjectCategoryDomainService.add(subjectCategoryBO);
            return Result.ok(true);
        } catch (Exception e) {
            log.error("添加分类信息失败：{}", e.getMessage());
            return Result.fail(false);
        }
    }
    @PostMapping("/queryPrimaryCategory")
    public Result<List<SubjectCategoryDTO>> queryPrimaryCategory(@RequestBody  SubjectCategoryDTO subjectCategoryDTO) {
        try {
            if(log.isInfoEnabled()){
                log.info("查询一级分类信息{}", JSON.toJSONString(subjectCategoryDTO));
            }
            SubjectCategoryBO subjectCategoryBO = SubjectCatrgoryDTOConverter.INSTANCE.convert(subjectCategoryDTO);
            List<SubjectCategoryBO> subjectCategoryList = subjectCategoryDomainService.queryPrimaryCategory(subjectCategoryBO);
            List<SubjectCategoryDTO> subjectCategoryDTOList = SubjectCatrgoryDTOConverter.INSTANCE.convert(subjectCategoryList);
            return Result.ok(subjectCategoryDTOList);
        } catch (Exception e) {
            log.error("查询一级分类信息失败：{}", e.getMessage());
            return Result.fail("查询失败");
        }
    }
    @PostMapping("/queryCategoryByPrimary")
    public Result<List<SubjectCategoryDTO>> queryCategoryByPrimary(@RequestBody SubjectCategoryDTO subjectCategoryDTO) {
        try {
            if(log.isInfoEnabled()){
                log.info("查询大类下分类信息:{}", JSON.toJSONString(subjectCategoryDTO));
            }
            Preconditions.checkNotNull(subjectCategoryDTO.getParentId(), "父级分类不能为空");
            SubjectCategoryBO subjectCategoryBO = SubjectCatrgoryDTOConverter.INSTANCE.convert(subjectCategoryDTO);
            List<SubjectCategoryBO> subjectCategoryList = subjectCategoryDomainService.queryCategory(subjectCategoryBO);
            List<SubjectCategoryDTO> subjectCategoryDTOList = SubjectCatrgoryDTOConverter.INSTANCE.convert(subjectCategoryList);
            return Result.ok(subjectCategoryDTOList);
        } catch (Exception e) {
            log.error("查询大类下分类信息失败：{}", e.getMessage(),e);
            return Result.fail("查询失败");
        }
    }

    @PostMapping("/update")
    public Result<Boolean> update(@RequestBody SubjectCategoryDTO subjectCategoryDTO) {
        try {
            if(log.isInfoEnabled()){
                log.info("修改分类信息：{}", subjectCategoryDTO);
            }
            Preconditions.checkNotNull(subjectCategoryDTO.getId(), "分类id不能为空");
            SubjectCategoryBO subjectCategoryBO = SubjectCatrgoryDTOConverter.INSTANCE.convert(subjectCategoryDTO);
            Boolean result = subjectCategoryDomainService.update(subjectCategoryBO);
            return Result.ok(result);
        }
        catch (Exception e) {
            log.error("修改分类信息失败：{}", e.getMessage());
            return Result.fail(false);
        }
    }
    @PostMapping("/delete")
    public Result<Boolean> delete(@RequestBody SubjectCategoryDTO subjectCategoryDTO) {
        try {
            if(log.isInfoEnabled()){
                log.info("删除分类信息：{}", subjectCategoryDTO);
            }
            Preconditions.checkNotNull(subjectCategoryDTO.getId(), "分类id不能为空");
            SubjectCategoryBO subjectCategoryBO = SubjectCatrgoryDTOConverter.INSTANCE.convert(subjectCategoryDTO);
            Boolean result = subjectCategoryDomainService.delete(subjectCategoryBO);
            return Result.ok(result);
        }
        catch (Exception e) {
            log.error("删除分类信息失败：{}", e.getMessage());
            return Result.fail(false);
        }
    }
    /**
     * 查询分类及标签一次性
     */
    @PostMapping("/queryCategoryAndLabel")
    public Result<List<SubjectCategoryDTO>> queryCategoryAndLabel(@RequestBody SubjectCategoryDTO subjectCategoryDTO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("SubjectCategoryController.queryCategoryAndLabel.dto:{}"
                        , JSON.toJSONString(subjectCategoryDTO));
            }
            Preconditions.checkNotNull(subjectCategoryDTO.getId(), "分类id不能为空");
            SubjectCategoryBO subjectCategoryBO = SubjectCatrgoryDTOConverter.INSTANCE.
                    convert(subjectCategoryDTO);
            List<SubjectCategoryBO> subjectCategoryBOList = subjectCategoryDomainService.queryCategoryAndLabel(subjectCategoryBO);
            List<SubjectCategoryDTO> dtoList = new LinkedList<>();
            subjectCategoryBOList.forEach(bo -> {
                SubjectCategoryDTO dto = SubjectCatrgoryDTOConverter.INSTANCE.convert(bo);
                List<SubjectLabelDTO> labelDTOList = SubjectLableDTOConverter.INSTANCE.convert(bo.getLabelBOList());
                dto.setLabelDTOList(labelDTOList);
                dtoList.add(dto);
            });
            return Result.ok(dtoList);
        } catch (Exception e) {
            log.error("SubjectCategoryController.queryPrimaryCategory.error:{}", e.getMessage(), e);
            return Result.fail("查询失败");
        }
    }

}
