package com.geekluxun.greateapp.entity;

import com.geekluxun.greateapp.common.BaseEntity;

public class SysUserRole extends BaseEntity{
    private Long userId;

    private Long roleId;

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