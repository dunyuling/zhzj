package com.aifeng.controller;

import com.aifeng.Util;
import com.aifeng.constant.ImgPath;
import com.aifeng.constant.ReligionType;
import com.aifeng.service.ConferenceHallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

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
    public String conferenceHall(@RequestParam(value = "page", defaultValue = "0") int page,
                                 HttpServletRequest request, Model model) {
        try {
            ReligionType religionType = Util.getDefaultReligionType(request);
            model.addAttribute("conferenceHalls", conferenceHallService.findAll(religionType, page))
                    .addAttribute("religionType", religionType);//TODO 要分标签管理
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "conference";
    }

    @RequestMapping("/conferenceHall_select.do")
    public String conferenceHallSelect(@RequestParam(value = "page", defaultValue = "0") int page,
                                       HttpServletRequest request, Model model) {
        try {
            ReligionType religionType = ReligionType.valueOf(request.getParameter("religionType"));
            model.addAttribute("conferenceHalls", conferenceHallService.findAll(religionType, page));
            model.addAttribute("toValidIds", request.getSession().getAttribute("toValidIds"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "conference_select";
    }

    @RequestMapping("/conferenceHall_toAdd.do")
    public String conferenceHallToAdd() {
        return "conferenceHall_add";
    }

    @RequestMapping("/conferenceHall_add.do")
    public String conferenceHallAdd(HttpServletRequest request) {
        ReligionType religionType = ReligionType.BUDDHISM;
        try {
            String imgPath = Util.uploadImg(request, ImgPath.conferenceHallPath);
            String name = request.getParameter("name");
            String address = request.getParameter("address");
            religionType = ReligionType.valueOf(request.getParameter("religionType"));
            String externalLink = request.getParameter("externalLink");
            conferenceHallService.saveConferenceHall(name, address, imgPath, religionType, externalLink);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/conferenceHall.do?religionType=" + religionType;
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
        ReligionType religionType = ReligionType.BUDDHISM;
        try {
            String imgRelativePath = Util.editImg(request, ImgPath.conferenceHallPath);
            long id = Long.parseLong(request.getParameter("id"));
            String name = request.getParameter("name");
            String address = request.getParameter("address");
            religionType = ReligionType.valueOf(request.getParameter("religionType"));
            String externalLink = request.getParameter("externalLink");
            conferenceHallService.editConferenceHall(id, name, address, imgRelativePath, religionType, externalLink);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/conferenceHall.do?religionType=" + religionType;
    }

    @RequestMapping("/conferenceHall_del")
    public String conferenceHallDel(HttpServletRequest request) {
        ReligionType religionType = ReligionType.BUDDHISM;
        try {
            String imgRealPathDir = request.getSession().getServletContext().getRealPath(ImgPath.conferenceHallPath);
            long id = Long.parseLong(request.getParameter("id"));
            religionType = conferenceHallService.delConferenceHall(imgRealPathDir, id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/conferenceHall.do?religionType=" + religionType;
    }
}
