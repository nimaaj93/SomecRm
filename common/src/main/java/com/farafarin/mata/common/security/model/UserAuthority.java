package com.farafarin.mata.common.security.model;

import org.springframework.security.core.GrantedAuthority;

/**
 * Created by K550 VX on 11/12/2017.
 */
public class UserAuthority implements GrantedAuthority {

    private Long roleId;
    private String roleName;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public String getAuthority() {
        return this.roleName;
    }
}
