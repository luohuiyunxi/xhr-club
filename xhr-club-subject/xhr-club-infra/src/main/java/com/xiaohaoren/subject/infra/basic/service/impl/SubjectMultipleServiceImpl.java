package com.xiaohaoren.subject.infra.basic.service.impl;

import com.xiaohaoren.subject.infra.basic.entity.SubjectMultiple;
import com.xiaohaoren.subject.infra.basic.mapper.SubjectMultipleDao;
import com.xiaohaoren.subject.infra.basic.service.SubjectMultipleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 澶氶€夐淇℃伅琛?SubjectMultiple)琛ㄦ湇鍔″疄鐜扮被
 *
 * @author makejava
 * @since 2023-10-05 21:30:05
 */
@Service("subjectMultipleService")
public class SubjectMultipleServiceImpl implements SubjectMultipleService {
    @Resource
    private SubjectMultipleDao subjectMultipleDao;

    /**
     * 閫氳繃ID鏌ヨ鍗曟潯鏁版嵁
     *
     * @param id 涓婚敭
     * @return 瀹炰緥瀵硅薄
     */
    @Override
    public SubjectMultiple queryById(Long id) {
        return this.subjectMultipleDao.queryById(id);
    }


    /**
     * 鏂板鏁版嵁
     *
     * @param subjectMultiple 瀹炰緥瀵硅薄
     * @return 瀹炰緥瀵硅薄
     */
    @Override
    public SubjectMultiple insert(SubjectMultiple subjectMultiple) {
        this.subjectMultipleDao.insert(subjectMultiple);
        return subjectMultiple;
    }

    /**
     * 淇敼鏁版嵁
     *
     * @param subjectMultiple 瀹炰緥瀵硅薄
     * @return 瀹炰緥瀵硅薄
     */
    @Override
    public SubjectMultiple update(SubjectMultiple subjectMultiple) {
        this.subjectMultipleDao.update(subjectMultiple);
        return this.queryById(subjectMultiple.getId());
    }

    /**
     * 閫氳繃涓婚敭鍒犻櫎鏁版嵁
     *
     * @param id 涓婚敭
     * @return 鏄惁鎴愬姛
     */
    @Override
    public boolean deleteById(Long id) {
        return this.subjectMultipleDao.deleteById(id) > 0;
    }

    @Override
    public void batchInsert(List<SubjectMultiple> subjectMultipleList) {
        this.subjectMultipleDao.insertBatch(subjectMultipleList);
    }

    @Override
    public List<SubjectMultiple> queryByCondition(SubjectMultiple subjectMultiple) {
        return this.subjectMultipleDao.queryAllByLimit(subjectMultiple);
    }
}
