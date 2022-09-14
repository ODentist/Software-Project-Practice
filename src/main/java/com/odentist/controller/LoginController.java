package com.odentist.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class LoginController {
//    @RequestMapping("/login")
//    public String login(
//            @RequestParam("username") String username ,
//            @RequestParam("password") String password,
//            Model model, HttpSession session){
//        //具体的业务
//        if(!StringUtils.isEmpty(username)&&"123456".equals(password)){
//            session.setAttribute("loginUser",username);
//            return "redirect:/main.html";
//        }
//        else{
//            //告诉用户，你登录失败
//            model.addAttribute("msg","用户名或者密码错误！");
//            return "index";
//        }
//    }

    @RequestMapping("/toLogin")
    public String login(){
        return "index";
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/user/toLogin";
    }

    @RequestMapping("/toHome")
    public String toHome(){
        return "home";
    }


}
