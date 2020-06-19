package com.JavaEE.homework.controller;

import com.JavaEE.homework.constant.WebConstant;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    // 登录页面
    @RequestMapping("/login.html")
    public String loginHtml() {
        return "login";
    }

    // 注册页面
    @RequestMapping("/register.html")
    public String registerHtml() {
        return "register";
    }

    // 退出登录
    @RequestMapping("/login.out")
    public String loginOut(HttpSession session) {
        session.removeAttribute(WebConstant.SESSION_KEY_USER);
        return "login";
    }

}
