package com.longing.demo.shiro.controller;


import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.longing.demo.shiro.model.User;

import javax.servlet.http.HttpServletRequest;

@Controller
public class UserController {

    @RequestMapping("/user/index")
    public String add(HttpServletRequest request){
        User bean = (User) SecurityUtils.getSubject().getPrincipal();
        request.setAttribute("userName", bean.getName());
        return "/user/index";
    }

    @RequestMapping("/vip/index")
    public String update(){
        return "/vip/index";
    }
}
