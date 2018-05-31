package com.farafarin.mata.common.entity;

import com.farafarin.mata.common.security.model.UserProfile;
import com.farafarin.mata.common.util.Empty;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Date;

/**
 * Created by Asus on 11/2/2017.
 */
public class BaseEntity {
    private String createUserName;
    private Date createDate;
    private String updateUserName;
    private Date updateDate;

    public String getCreateUserName() {
        if (Empty.isNotEmpty(createUserName)) {
            return createUserName;
        }
        try {

            UserProfile userProfile = (UserProfile) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            return userProfile.getUsername();
        } catch (Exception ex) {
            return "UNKNOWN";
        }
    }

    public void setCreateUserName(String createUserName) {

        this.createUserName = createUserName;
    }

    public Date getCreateDate() {
        if (Empty.isNotEmpty(createDate)) {
            return createDate;
        }
        return new Date();
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getUpdateUserName() {
        if (Empty.isNotEmpty(updateUserName)) {
            return updateUserName;
        }
        try {

            UserProfile userProfile = (UserProfile) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            return userProfile.getUsername();
        } catch (Exception ex) {
            return "UNKNOWN";
        }
    }

    public void setUpdateUserName(String updateUserName) {
        this.updateUserName = updateUserName;
    }

    public Date getUpdateDate() {
        if (Empty.isNotEmpty(updateDate)) {
            return updateDate;
        }
        return new Date();
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}
