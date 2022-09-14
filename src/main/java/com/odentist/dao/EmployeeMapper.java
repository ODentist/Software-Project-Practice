package com.odentist.dao;

import com.odentist.pojo.Employee;
import org.springframework.stereotype.Repository;

import java.util.List;

//员工dao
@Repository //被string托管
public interface EmployeeMapper {
    /**
     * 添加员工
     * @param employee
     */
    public void save(Employee employee);


    /**
     * 查询全部员工
     * @return
     */
    public List<Employee> getAll();

    /**
     * 通过id查询员工
     * @param id
     * @return
     */
    public Employee getEmployeeById(Integer id);

    /**
     * 通过id员工
     * @param id
     */
    public void delete(Integer id);

    /**
     * 查询所有部门信息
     * @return
     */
    public List<String> findAllDepartment();

    /**
     * 更新员工信息
     * @param employee
     */
    public void updEmpById(Employee employee);

    public Employee findEmpByEmail(String name);
}
