package com.aifeng.controller;

import com.aifeng.Util;
import com.aifeng.constant.ImgPath;
import com.aifeng.constant.InformationPublisher;
import com.aifeng.constant.VerifyStatus;
import com.aifeng.service.InformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

/**
 * Created by pro on 17-3-17.
 */
@Controller
public class InformationController {

    private final InformationService informationService;

    @Autowired
    public InformationController(InformationService informationService) {
        this.informationService = informationService;
    }

    @RequestMapping(value = "/information.do")
    public String informationGov(HttpServletRequest request, Model model) {
        try {
            String ip_ = request.getParameter("ip");
            InformationPublisher ip = InformationPublisher.valueOf(ip_);
            model.addAttribute("informations", informationService.findAll(ip));
            model.addAttribute("ip", ip_);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "information";
    }

    @RequestMapping("/information_toAdd.do")
    public String informationToAdd(HttpServletRequest request, Model model) {
        try {
            model.addAttribute("ip", request.getParameter("ip"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "information_add";

    }

    @RequestMapping("/information_add.do")
    public String informationGovAdd(HttpServletRequest request) {
        String ip = "gov";
        try {
            String imgRelativePath = Util.uploadImg(request, ImgPath.informationPath);
            String title = request.getParameter("title");
            String content = request.getParameter("content");

            ip = request.getParameter("ip");
            informationService.saveInformation(title, imgRelativePath, content, InformationPublisher.valueOf(ip));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/information.do?ip=" + ip;
    }

    @RequestMapping("/information_toEdit.do")
    public String informationToEdit(HttpServletRequest request, Model model) {
        loadModel(request, model);
        return "information_edit";
    }

    @RequestMapping("/information_edit.do")
    public String informationEdit(HttpServletRequest request) {
        String ip = "gov";
        try {
            String imgRelativePath = Util.editImg(request, ImgPath.informationPath);
            long id = Long.parseLong(request.getParameter("id"));
            String name = request.getParameter("title");
            String content = request.getParameter("content");
            ip = request.getParameter("ip");
            informationService.editInformation(id, name, content, imgRelativePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/information.do?ip=" + ip;
    }

    @RequestMapping("/information_toVerify.do")
    public String informationToVerify(HttpServletRequest request, Model model) {
        loadModel(request, model);
        return "information_verify";
    }

    @RequestMapping("/information_verify.do")
    public String informationVerify(HttpServletRequest request) {
        String ip = "gov";
        try {
            long id = Long.parseLong(request.getParameter("id"));
            VerifyStatus verifyStatus = VerifyStatus.valueOf(request.getParameter("verifyStatus"));
            String denyReason = request.getParameter("denyReason");
            informationService.verifyInformation(id, verifyStatus, denyReason);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/information.do?ip=" + ip;
    }

    //to edit and verify
    private void loadModel(HttpServletRequest request, Model model) {
        try {
            String ip = request.getParameter("ip");
            long id = Long.parseLong(request.getParameter("id"));
            model.addAttribute("information", informationService.findInformation(id)).addAttribute("ip", ip);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/information_del.do")
    public String informationGovDel(HttpServletRequest request) {
        String ip = "gov";
        try {
            String imgRealPathDir = request.getSession().getServletContext().getRealPath(ImgPath.informationPath);
            long id = Long.parseLong(request.getParameter("id"));
            ip = request.getParameter("ip");
            informationService.delInformation(id, imgRealPathDir);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/information.do?ip=" + ip;
    }
}