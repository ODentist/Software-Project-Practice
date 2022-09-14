package com.odentist.dao;

import com.odentist.pojo.Role;

import java.util.List;

/**
 * @Classname RoleMapper
 * @Date 2022/9/13 17:03
 * @Author jin
 */
public interface RoleMapper {
    public List<Role> findRole(String role);
}
