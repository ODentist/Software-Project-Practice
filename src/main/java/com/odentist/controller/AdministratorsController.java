package com.odentist.controller;

import com.odentist.dao.AdministratorsMapper;
import com.odentist.pojo.Administrators;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

//管理员列表
@Controller
@RequestMapping("/administrators")
public class AdministratorsController {

    @Resource
    AdministratorsMapper administratorsMapper;

    @RequestMapping("/admins")
    public String list(Model model){
        List<Administrators> administratorsList = administratorsMapper.getAll();
        model.addAttribute("admins",administratorsList);
        return "admin/list";
    }

    @GetMapping("/toAddPage")
    public String toAddPage(Model model){
        List<String> roles = new ArrayList<>();
        roles.add("super_administrator");
        roles.add("administrators");
        model.addAttribute("roles",roles);
        return "admin/add";
    }

    @PostMapping ("/addAdmin")
    public String addAdmin(Administrators administrators){
        //保存管理员的信息
        administratorsMapper.save(administrators);
        return "redirect:/administrators/admins";
    }

    //管理员修改页面
    @GetMapping("/toUpdateAdmin/{id}")
    public String toUpdateAdmin(@PathVariable("id") Integer id,Model model){
        Administrators admin = administratorsMapper.getAdministratorsById(id);
        model.addAttribute("admin",admin);

        List<String> roles = new ArrayList<>();
        roles.add("super_administrator");
        roles.add("administrators");
        model.addAttribute("roles",roles);
        return "admin/update";
    }
    @PostMapping("/updateAdmin")
    public String update(Administrators administrators){
        administratorsMapper.updAdministratorsById(administrators);
        return "redirect:/administrators/admins";
    }

    //删除管理员
    @GetMapping("/delAdmin/{id}")
    public String delAdmin(@PathVariable("id") Integer id){
        administratorsMapper.delete(id);
        return "redirect:/administrators/admins";
    }
}
