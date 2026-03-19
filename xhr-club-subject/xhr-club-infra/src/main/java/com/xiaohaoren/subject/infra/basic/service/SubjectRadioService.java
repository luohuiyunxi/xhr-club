package com.xiaohaoren.subject.infra.basic.service;

import com.xiaohaoren.subject.infra.basic.entity.SubjectRadio;

import java.util.List;

/**
 * 鍗曢€夐淇℃伅琛?SubjectRadio)琛ㄦ湇鍔℃帴鍙?
 *
 * @author makejava
 * @since 2023-10-05 21:30:19
 */
public interface SubjectRadioService {

    /**
     * 閫氳繃ID鏌ヨ鍗曟潯鏁版嵁
     *
     * @param id 涓婚敭
     * @return 瀹炰緥瀵硅薄
     */
    SubjectRadio queryById(Long id);

    /**
     * 鏂板鏁版嵁
     *
     * @param subjectRadio 瀹炰緥瀵硅薄
     * @return 瀹炰緥瀵硅薄
     */
    SubjectRadio insert(SubjectRadio subjectRadio);

    /**
     * 鎵归噺鎻掑叆
     */
    void batchInsert(List<SubjectRadio> subjectRadioList);

    /**
     * 淇敼鏁版嵁
     *
     * @param subjectRadio 瀹炰緥瀵硅薄
     * @return 瀹炰緥瀵硅薄
     */
    SubjectRadio update(SubjectRadio subjectRadio);

    /**
     * 閫氳繃涓婚敭鍒犻櫎鏁版嵁
     *
     * @param id 涓婚敭
     * @return 鏄惁鎴愬姛
     */
    boolean deleteById(Long id);

    List<SubjectRadio> queryByCondition(SubjectRadio subjectRadio);

}
