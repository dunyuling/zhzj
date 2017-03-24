package com.aifeng.controller;

import com.aifeng.Util;
import com.aifeng.constant.ContentType;
import com.aifeng.constant.ImgPath;
import com.aifeng.constant.ReligionType;
import com.aifeng.service.ConferenceHallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

/**
 * Created by pro on 17-3-24.
 */
@Controller
public class ConferenceHallController {

    private final ConferenceHallService conferenceHallService;

    @Autowired
    public ConferenceHallController(ConferenceHallService conferenceHallService) {
        this.conferenceHallService = conferenceHallService;
    }

    @RequestMapping("/conferenceHall.do")
    public String conferenceHall(@RequestParam(value = "page", defaultValue = "0") int page, Model model) {
        try {
            model.addAttribute("conferenceHalls", conferenceHallService.findAll(ContentType.console, page));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "conference";
    }

    @RequestMapping("/conferenceHall_toAdd.do")
    public String conferenceHallToAdd() {
        return "conferenceHall_add";
    }

    @RequestMapping("/conferenceHall_add.do")
    public String conferenceHallAdd(HttpServletRequest request) {
        try {
            String imgPath = Util.uploadImg(request, ImgPath.conferenceHallPath);
            String name = request.getParameter("name");
            String address = request.getParameter("address");
            ReligionType religionType = ReligionType.valueOf(request.getParameter("religionType"));
            String externalLink = request.getParameter("externalLink");
            conferenceHallService.saveConferenceHall(name, address, imgPath, religionType, externalLink);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/conferenceHall.do";
    }

    @RequestMapping("/conferenceHall_toEdit.do")
    public String conferenceHallToEdit(HttpServletRequest request, Model model) {
        try {
            long id = Long.parseLong(request.getParameter("id"));
            model.addAttribute("conferenceHall", conferenceHallService.findConferenceHall(id));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "conferenceHall_edit";
    }

    @RequestMapping("/conferenceHall_edit.do")
    public String conferenceHallEdit(HttpServletRequest request) {
        try {
            String imgRelativePath = Util.editImg(request, ImgPath.conferenceHallPath);
            long id = Long.parseLong(request.getParameter("id"));
            String name = request.getParameter("name");
            String address = request.getParameter("address");
            ReligionType religionType = ReligionType.valueOf(request.getParameter("religionType"));
            String externalLink = request.getParameter("externalLink");
            conferenceHallService.editConferenceHall(id, name, address, imgRelativePath, religionType, externalLink);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/conferenceHall.do";
    }

    @RequestMapping("/conferenceHall_del")
    public String conferenceHallDel(HttpServletRequest request) {
        try {
            String imgRealPathDir = request.getSession().getServletContext().getRealPath(ImgPath.conferenceHallPath);
            long id = Long.parseLong(request.getParameter("id"));
            conferenceHallService.delConferenceHall(imgRealPathDir, id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/conferenceHall.do";
    }
}
