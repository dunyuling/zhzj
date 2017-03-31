package com.aifeng.controller;

import com.aifeng.Util;
import com.aifeng.constant.ImgPath;
import com.aifeng.constant.RedirectionType;
import com.aifeng.constant.ReligionType;
import com.aifeng.constant.VerifyStatus;
import com.aifeng.service.ChurchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by pro on 17-3-14.
 */
@Controller
public class ChurchController {

    private final ChurchService churchService;

    @Autowired
    public ChurchController(ChurchService churchService) {
        this.churchService = churchService;
    }

    @RequestMapping("/church.do")
    public String church(HttpServletRequest request, Model model) {
        try {
            ReligionType religionType = Util.getDefaultReligionType(request);
            VerifyStatus verifyStatus = VerifyStatus.valueOf(request.getParameter("verifyStatus"));
            model.addAttribute("churches", churchService.findAll(religionType, verifyStatus, 0))
                    .addAttribute("religionType", religionType);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "church";
    }

    @RequestMapping("/church_toAdd.do")
    public String churchToAdd() {
        return "church_add";
    }

    @RequestMapping("/church_add.do")
    public String churchAdd(HttpServletRequest request) {
        try {
//            String imgPath = Util.uploadImg(request, ImgPath.adPath);
            String name = request.getParameter("name");
            String address = request.getParameter("telephone");
            String telephone = request.getParameter("address");
            ReligionType religionType = ReligionType.valueOf(request.getParameter("religionType"));

            churchService.saveChurch(name, address, telephone, religionType);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/church.do";
    }

    @RequestMapping("/church_toEdit.do")
    public String churchToEdit(HttpServletRequest request, Model model) {
        try {
            long id = Long.parseLong(request.getParameter("id"));
            model.addAttribute("church", churchService.findChurch(id));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "church_edit";
    }

    @RequestMapping("/church_edit.do")
    public String churchEdit(HttpServletRequest request) {
        try {
//            String imgRelativePath = Util.editImg(request, ImgPath.adPath);
            long id = Long.parseLong(request.getParameter("id"));
            String name = request.getParameter("name");
            String telephone = request.getParameter("telephone");
            String address = request.getParameter("address");
            churchService.editChurch(id, name, address, telephone);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/church.do";
    }

    @RequestMapping("/church_del.do")
    public String churchDel(HttpServletRequest request) {
        try {
//            String imgRealPathDir = request.getSession().getServletContext().getRealPath(ImgPath.adPath);
            long id = Long.parseLong(request.getParameter("id"));
            churchService.delChurch(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/church.do";
    }
}