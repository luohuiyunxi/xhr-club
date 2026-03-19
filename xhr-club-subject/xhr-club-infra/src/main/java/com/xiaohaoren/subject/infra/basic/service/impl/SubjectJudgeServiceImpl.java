package com.xiaohaoren.subject.infra.basic.service.impl;

import com.xiaohaoren.subject.infra.basic.entity.SubjectJudge;
import com.xiaohaoren.subject.infra.basic.mapper.SubjectJudgeDao;
import com.xiaohaoren.subject.infra.basic.service.SubjectJudgeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 鍒ゆ柇棰?SubjectJudge)琛ㄦ湇鍔″疄鐜扮被
 *
 * @author makejava
 * @since 2023-10-05 21:29:47
 */
@Service("subjectJudgeService")
public class SubjectJudgeServiceImpl implements SubjectJudgeService {
    @Resource
    private SubjectJudgeDao subjectJudgeDao;

    /**
     * 閫氳繃ID鏌ヨ鍗曟潯鏁版嵁
     *
     * @param id 涓婚敭
     * @return 瀹炰緥瀵硅薄
     */
    @Override
    public SubjectJudge queryById(Long id) {
        return this.subjectJudgeDao.queryById(id);
    }

    /**
     * 鏂板鏁版嵁
     *
     * @param subjectJudge 瀹炰緥瀵硅薄
     * @return 瀹炰緥瀵硅薄
     */
    @Override
    public SubjectJudge insert(SubjectJudge subjectJudge) {
        this.subjectJudgeDao.insert(subjectJudge);
        return subjectJudge;
    }

    /**
     * 淇敼鏁版嵁
     *
     * @param subjectJudge 瀹炰緥瀵硅薄
     * @return 瀹炰緥瀵硅薄
     */
    @Override
    public SubjectJudge update(SubjectJudge subjectJudge) {
        this.subjectJudgeDao.update(subjectJudge);
        return this.queryById(subjectJudge.getId());
    }

    /**
     * 閫氳繃涓婚敭鍒犻櫎鏁版嵁
     *
     * @param id 涓婚敭
     * @return 鏄惁鎴愬姛
     */
    @Override
    public boolean deleteById(Long id) {
        return this.subjectJudgeDao.deleteById(id) > 0;
    }

    @Override
    public List<SubjectJudge> queryByCondition(SubjectJudge subjectJudge) {
        return this.subjectJudgeDao.queryAllByLimit(subjectJudge);
    }
}
