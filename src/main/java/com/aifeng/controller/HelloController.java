package com.aifeng.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    HelloController() {
        System.out.println("+++++++++++++++++++++++++++");
    }

    @RequestMapping("/hello")
    public @ResponseBody
    String test() {
        return "hello, world! This com from spring!";
    }

    @RequestMapping("/hello1")
    public String test1(Model model) {
        model.addAttribute("message","lhg");
        return "message";
    }
}
