package com.odentist.controller;

import com.odentist.dao.AdministratorsMapper;
import com.odentist.pojo.Administrators;
import com.odentist.utils.SendMail;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @Classname RegisterController
 * @Date 2022/9/13 13:50
 * @Author jin
 */
@Controller
@RequestMapping("/register")
public class RegisterController {

    @Resource
    AdministratorsMapper administratorsMapper;

    @RequestMapping("/toRegister")
    public String register(){
        return "register";
    }

    @RequestMapping("/sendCode/{email}")
    @ResponseBody
    public String sendCode(@PathVariable("email") String email, HttpServletRequest request) throws Exception {
        String code = SendMail.sendQQEmail("oden1902021123@163.com", "VWEGSQIKXSSUPBAS", email);
        HttpSession session = request.getSession();
        session.setAttribute("code",code);
        return "发送成功";
    }

    @RequestMapping("/doRegister")
    @ResponseBody
    public String doRegister(String username,String password,String code,HttpServletRequest request){
        if (request.getSession().getAttribute("code").equals(code)){
            Administrators administrators = new Administrators(username, password, "administrators");
            administratorsMapper.save(administrators);
            return "注册成功";
        }else {
            return "验证码错误";
        }
    }
}
