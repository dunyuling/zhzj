package com.aifeng.controller;

import com.aifeng.model.Believer;
import com.aifeng.service.BelieverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by pro on 17-3-10.
 */
@Controller
public class LoginController {

    @Autowired
    BelieverService believerService;

    @RequestMapping("/toLogin.do")
    public String toLogin() {
        return "login";
    }

    @RequestMapping(value="/login.do",method = RequestMethod.POST)
    public String login( HttpServletRequest request,Model model) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        Believer believer = believerService.findUser(username,password);
        if(believer != null) {
            request.getSession().setAttribute("username",username);
            return "index";
        } else {
            model.addAttribute("errMsg","用户名或者密码错误");
            return "redirect:/toLogin.do";
        }
    }

    @RequestMapping("/index.do")
    public String index(HttpServletRequest request) {
        String username = (String) request.getSession().getAttribute("username");
        if(username != null && !username.isEmpty()) {
            return "index";
        } else {
            return "redirect:/toLogin.do";
        }
    }

    @RequestMapping("/logout.do")
    public String logout(HttpServletRequest request) {
        request.getSession().removeAttribute("username");
        return "redirect:/toLogin.do";
    }
}