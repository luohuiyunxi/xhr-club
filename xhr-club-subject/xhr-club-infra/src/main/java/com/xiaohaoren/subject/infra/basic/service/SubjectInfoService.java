package com.xiaohaoren.subject.infra.basic.service;

import com.xiaohaoren.subject.infra.basic.entity.SubjectInfo;

import java.util.List;


/**
 * 题目信息表(SubjectInfo)表服务接口
 *
 * @author makejava
 * @since 2025-10-04 23:15:05
 */
public interface SubjectInfoService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SubjectInfo queryById(Long id);



    /**
     * 新增数据
     *
     * @param subjectInfo 实例对象
     * @return 实例对象
     */
    SubjectInfo insert(SubjectInfo subjectInfo);

    /**
     * 修改数据
     *
     * @param subjectInfo 实例对象
     * @return 实例对象
     */
    SubjectInfo update(SubjectInfo subjectInfo);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);
    /**
     * 通过条件查询数据
     *
     * @param subjectInfo 筛选条件
     * @return 查询结果
     */
    int countByCondition(SubjectInfo subjectInfo, Long categoryId, Long labelId);
    /**
     * 通过条件查询数据
     *
     * @param subjectInfo 筛选条件
     * @return 筛选结果
     */
    List<SubjectInfo> queryByCondition(SubjectInfo subjectInfo, Long categoryId, Long labelId, int start, Integer pageSize);

    Long querySubjectIdCursor(Long subjectId, Long categoryId, Long labelId, int cursor);
}
