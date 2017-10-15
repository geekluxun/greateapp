package com.geekluxun.greateapp.dao;

import com.geekluxun.greateapp.entity.SysUserRole;

public interface SysUserRoleMapper {
    int insert(SysUserRole record);

    int insertSelective(SysUserRole record);
}