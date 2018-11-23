package com.micolor.common.utils.tablelist;

import java.util.List;

/**
 * @Author Ann
 * @Date 2018/11/23 下午9:42
 * @Description
 */
public class TableList {
    //开始页面
    private Integer start;
    //页面展现条数
    private Integer length;
    //where条件
    private List<SerachBy> searchBy;
    //排序
    private String orderBy;

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public List<SerachBy> getSearchBy() {
        return searchBy;
    }

    public void setSearchBy(List<SerachBy> searchBy) {
        this.searchBy = searchBy;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }
}
