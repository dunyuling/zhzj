package com.aifeng.controller;

import com.aifeng.service.InformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by pro on 17-3-17.
 */
@Controller
public class InformationController {

    @Autowired
    InformationService informationService;

    @RequestMapping("/information_gov.do")
    public String informationGov() {

        return "information_gov";
    }

    @RequestMapping("/information_gov_toAdd.do")
    public String informationGovToAdd() {

        return "information_gov_add";
    }

    @RequestMapping("/information_gov_add.do")
    public String informationGovAdd(HttpServletRequest request) {

        return "redirect:/information_gov.do";
    }

    @RequestMapping("/information_gov_toEdit.do")
    public String informationToEdit() {
        return "information_gov_edit";
    }

    @RequestMapping("/information_gov_edit.do")
    public String informationEdit() {
        return "redirect:/information_gov.do";
    }

    @RequestMapping("/information_gov_del")
    public String informationGovDel(){
        return "redirect:/information_gov.do";
    }
}
