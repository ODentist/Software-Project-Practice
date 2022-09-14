package com.odentist.controller;

import com.odentist.dao.EmployeeMapper;
import com.odentist.pojo.Employee;
import com.odentist.service.AdministratorsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//员工列表
@Controller
@RequestMapping("/emp")
public class EmployeeController {

    @Autowired
    EmployeeMapper employeeDao;

    @Autowired
    AdministratorsService administratorsService;

    @RequestMapping("/emps")
    public String list(Model model){
        List<Employee> employees = employeeDao.getAll();
        model.addAttribute("emps",employees);
        return "emp/list";
    }

    @GetMapping("/toAddPage")
    public String toAddPage(Model model){
        //查询所有的部门信息
        List<String> departments = employeeDao.findAllDepartment();
        model.addAttribute("departments",departments);
        return "emp/add";
    }

    @PostMapping ("/addEmp")
    public String addEmp(Employee employee){
        //保存员工的信息
        employeeDao.save(employee);
        return "redirect:/emp/emps";
    }

    //员工修改页面
    @GetMapping("/toUpdateEmp/{id}")
    public String toUpdateEmp(@PathVariable("id") Integer id,Model model){
        Employee employee = employeeDao.getEmployeeById(id);
        model.addAttribute("emp",employee);

        //查询所有的部门信息
        List<String> departments = employeeDao.findAllDepartment();
        model.addAttribute("departments",departments);
        return "emp/update";
    }
    @PostMapping("/updateEmp")
    public String update(Employee employee){
        employeeDao.updEmpById(employee);
        return "redirect:/emp/emps";
    }

    //删除员工
    @GetMapping("/delEmp/{id}")
    public String delEmp(@PathVariable("id") Integer id){
        employeeDao.delete(id);
        return "redirect:/emp/emps";
    }

    @GetMapping("/myInfo")
    public String myInfo(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        Employee emp = employeeDao.findEmpByEmail(name);
        model.addAttribute("emp",emp);
        //查询所有的部门信息
        List<String> departments = employeeDao.findAllDepartment();
        model.addAttribute("departments",departments);
        return "/emp/myInfo";
    }

    @PostMapping("/updateMy")
    public String updateMy(Employee employee){
        employeeDao.updEmpById(employee);
        return "redirect:/emp/myInfo";
    }
}
