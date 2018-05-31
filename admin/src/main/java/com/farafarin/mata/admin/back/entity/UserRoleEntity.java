package com.farafarin.mata.admin.back.entity;

import com.farafarin.mata.common.entity.BaseEntity;

/**
 * Created by Asus on 11/11/2017.
 */
public class UserRoleEntity extends BaseEntity {
    private Long userRoleId;
    private Long userId;
    private Long roleId;

    public Long getUserRoleId() {
        return userRoleId;
    }

    public void setUserRoleId(Long userRoleId) {
        this.userRoleId = userRoleId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
}
