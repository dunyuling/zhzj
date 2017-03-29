package com.aifeng.controller;

import com.aifeng.Util;
import com.aifeng.constant.ImgPath;
import com.aifeng.constant.ReligionType;
import com.aifeng.service.CreedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by pro on 17-3-14.
 */
@Controller
public class CreedController {

    private final CreedService creedService;

    @Autowired
    public CreedController(CreedService creedService) {
        this.creedService = creedService;
    }

    @RequestMapping("/creed.do")
    public String creed(@RequestParam(value = "page", defaultValue = "0") int page,
                        HttpServletRequest request, Model model) {
        try {
            ReligionType religionType = Util.getDefaultReligionType(request);
            model.addAttribute("creeds", creedService.findAll(religionType, page))
                    .addAttribute("religionType", religionType);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "creed";
    }

    @RequestMapping("/creed_toAdd.do")
    public String creedToAdd() {
        return "creed_add";
    }

    @RequestMapping("/creed_add.do")
    public String creedAdd(HttpServletRequest request) {
        ReligionType religionType = ReligionType.佛教;
        try {
            String imgPath = Util.uploadImg(request, ImgPath.creedPath);
            String title = request.getParameter("title");
            String content = request.getParameter("remark");
            religionType = ReligionType.valueOf(request.getParameter("religionType"));
            creedService.saveCreed(title, content, imgPath, religionType);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/creed.do?religionType=" + ReligionType.getConParam(religionType);
    }

    @RequestMapping("/creed_toEdit.do")
    public String creedToEdit(HttpServletRequest request, Model model) {
        try {
            long id = Long.parseLong(request.getParameter("id"));
            model.addAttribute("creed", creedService.findCreed(id));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "creed_edit";
    }

    @RequestMapping("/creed_edit.do")
    public String creedEdit(HttpServletRequest request) {
        ReligionType religionType = ReligionType.佛教;
        try {
            String imgRelativePath = Util.editImg(request, ImgPath.creedPath);
            long id = Long.parseLong(request.getParameter("id"));
            String title = request.getParameter("title");
            String content = request.getParameter("remark");
            religionType = ReligionType.valueOf(request.getParameter("religionType"));
            creedService.editCreed(id, title, content, imgRelativePath, religionType);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/creed.do?religionType=" + ReligionType.getConParam(religionType);
    }

    @RequestMapping("/creed_del.do")
    public String adDel(HttpServletRequest request) {
        ReligionType religionType = ReligionType.佛教;
        try {
            String imgRealPathDir = request.getSession().getServletContext().getRealPath(ImgPath.creedPath);
            long id = Long.parseLong(request.getParameter("id"));
            religionType = creedService.delCreed(imgRealPathDir, id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/creed.do?religionType=" + ReligionType.getConParam(religionType);
    }
}
