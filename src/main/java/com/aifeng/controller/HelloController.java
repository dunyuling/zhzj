package com.aifeng.controller;

import com.aifeng.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    HelloController() {
        System.out.println("+++++++++++++++++++++++++++");
    }

    @Autowired
    TestService testService;

    @RequestMapping("/hello")
    public @ResponseBody
    String test() {
        testService.testSaveUser();
        return "hello, world! This com from spring!";
    }

    @RequestMapping("/hello1")
    public String test1(Model model) {
        model.addAttribute("message","lhg");
        return "message";
    }
}
