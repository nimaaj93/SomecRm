package com.farafarin.mata.common.searchobject;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

/**
 * Created by K550 VX on 12/26/2017.
 */
public abstract class BaseSO {

    @NotNull
    @Max(value = 20)
    private Integer pageSize;
    @NotNull
    private Integer pageNumber;

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Integer getOffset() {
        return this.pageNumber * this.pageSize;
    }

}
