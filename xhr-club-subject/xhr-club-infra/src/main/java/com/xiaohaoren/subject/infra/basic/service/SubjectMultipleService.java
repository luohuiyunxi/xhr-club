package com.xiaohaoren.subject.infra.basic.service;

import com.xiaohaoren.subject.infra.basic.entity.SubjectMultiple;

import java.util.List;

/**
 * 澶氶€夐淇℃伅琛?SubjectMultiple)琛ㄦ湇鍔℃帴鍙?
 *
 * @author makejava
 * @since 2023-10-05 21:30:05
 */
public interface SubjectMultipleService {

    /**
     * 閫氳繃ID鏌ヨ鍗曟潯鏁版嵁
     *
     * @param id 涓婚敭
     * @return 瀹炰緥瀵硅薄
     */
    SubjectMultiple queryById(Long id);

    /**
     * 鏂板鏁版嵁
     *
     * @param subjectMultiple 瀹炰緥瀵硅薄
     * @return 瀹炰緥瀵硅薄
     */
    SubjectMultiple insert(SubjectMultiple subjectMultiple);

    /**
     * 淇敼鏁版嵁
     *
     * @param subjectMultiple 瀹炰緥瀵硅薄
     * @return 瀹炰緥瀵硅薄
     */
    SubjectMultiple update(SubjectMultiple subjectMultiple);

    /**
     * 閫氳繃涓婚敭鍒犻櫎鏁版嵁
     *
     * @param id 涓婚敭
     * @return 鏄惁鎴愬姛
     */
    boolean deleteById(Long id);

    /**
     * 鎵归噺鎻掑叆
     */
    void batchInsert(List<SubjectMultiple> subjectMultipleList);

    List<SubjectMultiple> queryByCondition(SubjectMultiple subjectMultiple);

}
