package com.aifeng.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by pro on 17-3-10.
 */
@Controller
public class LoginController {

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/index")
    public String index(HttpServletRequest request) {
        String user = request.getParameter("user");
        if(user != null && user.equals("lhg")) {
            return "index";
        } else {
            return "redirect:/login";
        }
    }
}
