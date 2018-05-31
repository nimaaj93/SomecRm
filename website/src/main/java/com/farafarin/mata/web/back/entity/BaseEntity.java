package com.farafarin.mata.web.back.entity;

import java.util.Date;

/**
 * Created by Asus on 11/8/2017.
 */
public class BaseEntity {
    private String createUserName;
    private Date createDate;
    private String updateUserName;
    private Date updateDate;

    public String getCreateUserName() {

        return "1235847";
    }

    public void setCreateUserName(String createUserName) {

        this.createUserName = createUserName;
    }

    public Date getCreateDate() {
        return new Date();
    }

    public void setCreateDate(Date createDate) {
        this.createDate = new Date();
    }

    public String getUpdateUserName() {
        return "1235847";
    }

    public void setUpdateUserName(String updateUserName) {
        this.updateUserName = updateUserName;
    }

    public Date getUpdateDate() {
        return new Date();
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}
