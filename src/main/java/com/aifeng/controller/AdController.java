package com.aifeng.controller;

import com.aifeng.Util;
import com.aifeng.constant.ImgPath;
import com.aifeng.constant.RedirectionType;
import com.aifeng.service.AdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

/**
 * Created by pro on 17-3-14.
 */
@Controller
public class AdController {

    private final AdService adService;

    @Autowired
    public AdController(AdService adService) {
        this.adService = adService;
    }

    @RequestMapping("/ad.do")
    public String ad(Model model) {
        try {
            model.addAttribute("ads", adService.findAll());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "ad";
    }

    @RequestMapping("/ad_toAdd.do")
    public String adToAdd() {
        return "ad_add";
    }

    @RequestMapping("/ad_add.do")
    public String adAdd(HttpServletRequest request) {
        try {
            String imgPath = Util.uploadImg(request, ImgPath.adPath);
            String name = request.getParameter("name");
            String redirectionTypeStr = request.getParameter("redirectionType");
            String innerRedirectionType = request.getParameter("innerRedirectionType");
            String externalLink = request.getParameter("externalLink");
            adService.saveAd(name, imgPath, RedirectionType.valueOf(redirectionTypeStr), innerRedirectionType, externalLink);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/ad.do";
    }

    @RequestMapping("/ad_toEdit.do")
    public String adToEdit(HttpServletRequest request, Model model) {
        try {
            long id = Long.parseLong(request.getParameter("id"));
            model.addAttribute("ad", adService.findAd(id));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "ad_edit";
    }

    @RequestMapping("/ad_edit.do")
    public String adEdit(HttpServletRequest request) {
        try {
            String imgRelativePath = Util.editImg(request, ImgPath.adPath);
            long id = Long.parseLong(request.getParameter("id"));
            String name = request.getParameter("name");
            String redirectionTypeStr = request.getParameter("redirectionType");
            String innerRedirectionType = request.getParameter("innerRedirectionType");
            String externalLink = request.getParameter("externalLink");
            adService.editAd(id, name, imgRelativePath, RedirectionType.valueOf(redirectionTypeStr), innerRedirectionType, externalLink);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/ad.do";
    }

    @RequestMapping("/ad_del")
    public String adDel(HttpServletRequest request) {
        try {
            String imgRealPathDir = request.getSession().getServletContext().getRealPath(ImgPath.adPath);
            long id = Long.parseLong(request.getParameter("id"));
            adService.delAd(imgRealPathDir, id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/ad.do";
    }

    @RequestMapping("/ad_up")
    public String adUp() {
        return "ad";
    }

    @RequestMapping("/ad_down")
    public String adDown() {
        return "ad";
    }
}
