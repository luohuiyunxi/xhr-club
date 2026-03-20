package com.xiaohaoren.practice.server.dao;

import com.xiaohaoren.practice.server.entity.po.SubjectJudgePO;

public interface SubjectJudgeDao {


    SubjectJudgePO selectBySubjectId(Long repeatSubjectId);


}