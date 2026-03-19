package com.xiaohaoren.auth.common.entity;

import lombok.Data;

/**
 * 分页信息
 */
@Data

public class PageInfo {
    /**
     * 当前页码
     */
    private Integer pageNo = 1;
    /**
     * 每页显示数量
     */
    private Integer pageSize = 10;
    public Integer getPageNo(){
        if(pageNo < 1 || pageNo == null){
            return 1;
        }
        return pageNo;
    }
    public Integer getPageSize(){
        if(pageSize < 1 || pageSize == null){
            return 10;
        }
        return pageSize;
    }
}
