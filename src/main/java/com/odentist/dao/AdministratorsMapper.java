package com.odentist.dao;

import com.odentist.pojo.Administrators;

import java.util.List;

/**
 * @Classname AdministratorsMapper
 * @Date 2022/9/13 11:16
 * @Author jin
 */
public interface AdministratorsMapper {

    /**
     * 添加管理员
     * @param administrators
     */
    public void save(Administrators administrators);


    /**
     * 查询普通管理员
     * @return
     */
    public List<Administrators> getAll();
    

    /**
     * 通过id查询管理员
     * @param id
     * @return
     */
    public Administrators getAdministratorsById(Integer id);

    /**
     * 通过id管理员
     * @param id
     */
    public void delete(Integer id);

    /**
     * 更新管理员信息
     * @param administrators
     */
    public void updAdministratorsById(Administrators administrators);

    public Administrators findAdministratorsByUserName(String username);
}
