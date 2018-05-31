package com.farafarin.mata.common.entity;

import com.farafarin.mata.common.entity.BaseEntity;
import org.omg.CORBA.PRIVATE_MEMBER;

/**
 * Created by Asus on 11/16/2017.
 */
public class OrderCategoryEntity extends BaseEntity {

    private Long orderCategoryId;
    private String titleFa;
    private String titleEn;
    private Long parentId;

    public Long getOrderCategoryId() {
        return orderCategoryId;
    }

    public void setOrderCategoryId(Long orderCategoryId) {
        this.orderCategoryId = orderCategoryId;
    }

    public String getTitleFa() {
        return titleFa;
    }

    public void setTitleFa(String titleFa) {
        this.titleFa = titleFa;
    }

    public String getTitleEn() {
        return titleEn;
    }

    public void setTitleEn(String titleEn) {
        this.titleEn = titleEn;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }
}
