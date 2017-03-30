package com.aifeng.controller;

import com.aifeng.Util;
import com.aifeng.constant.ImgPath;
import com.aifeng.constant.ReligionType;
import com.aifeng.service.ScriptureService;
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
public class ScriptureController {

    private final ScriptureService scriptureService;

    @Autowired
    public ScriptureController(ScriptureService scriptureService) {
        this.scriptureService = scriptureService;
    }

    @RequestMapping("/scripture.do")
    public String scripture(@RequestParam(value = "page", defaultValue = "0") int page,
                            HttpServletRequest request, Model model) {
        try {
            ReligionType religionType = Util.getDefaultReligionType(request);
            model.addAttribute("scriptures", scriptureService.findAll(religionType, page))
                    .addAttribute("religionType", religionType);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "scripture";
    }

    @RequestMapping("/scripture_toAdd.do")
    public String scriptureToAdd() {
        return "scripture_add";
    }

    @RequestMapping("/scripture_add.do")
    public String scriptureAdd(HttpServletRequest request) {
        ReligionType religionType = ReligionType.BUDDHISM;
        try {
            String coverPath = Util.uploadImg(request, ImgPath.scripturePath);
            String descPath = Util.uploadImg(request, ImgPath.scripturePath, "descImg");
            String title = request.getParameter("title");
            String content = request.getParameter("remark");
            religionType = ReligionType.valueOf(request.getParameter("religionType"));
            scriptureService.saveScripture(title, content, coverPath, descPath, religionType);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/scripture.do?religionType=" + religionType;
    }

    @RequestMapping("/scripture_toEdit.do")
    public String scriptureToEdit(HttpServletRequest request, Model model) {
        try {
            long id = Long.parseLong(request.getParameter("id"));
            model.addAttribute("scripture", scriptureService.findScripture(id));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "scripture_edit";
    }

    @RequestMapping("/scripture_edit.do")
    public String scriptureEdit(HttpServletRequest request) {
        ReligionType religionType = ReligionType.BUDDHISM;
        try {
            String coverPath = Util.editImg(request, ImgPath.scripturePath);
            String descPath = Util.editImg(request, ImgPath.scripturePath, "descImg");
            long id = Long.parseLong(request.getParameter("id"));
            String title = request.getParameter("title");
            String content = request.getParameter("remark");
            religionType = ReligionType.valueOf(request.getParameter("religionType"));
            scriptureService.editScripture(id, title, content, coverPath, descPath, religionType);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/scripture.do?religionType=" + religionType;
    }

    @RequestMapping("/scripture_del.do")
    public String adDel(HttpServletRequest request) {
        ReligionType religionType = ReligionType.BUDDHISM;
        try {
            String imgRealPathDir = request.getSession().getServletContext().getRealPath(ImgPath.scripturePath);
            long id = Long.parseLong(request.getParameter("id"));
            religionType = scriptureService.delScripture(imgRealPathDir, id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/scripture.do?religionType=" + religionType;
    }
}
