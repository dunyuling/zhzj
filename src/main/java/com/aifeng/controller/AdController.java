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

    @Autowired
    AdService adService;

    @RequestMapping("/ad.do")
    public String ad(Model model) {
        model.addAttribute("ads",adService.findAll());
        return "ad";
    }

    @RequestMapping("/ad_toAdd.do")
    public String adToAdd() {
        return "ad_add";
    }

    @RequestMapping("/ad_add.do")
    public String adAdd(HttpServletRequest request) {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        String imgRealPathDir = request.getSession().getServletContext().getRealPath(ImgPath.adPath);
        Util.mkDir(imgRealPathDir);

        MultipartFile multipartFile = multipartRequest.getFile("img");
//        String suffix = multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf("."));
//        String logImageName = UUID.randomUUID().toString() + suffix;// 构建文件名称
        String imgImageName = multipartFile.getOriginalFilename();
        String fileName = imgRealPathDir + File.separator + imgImageName;

        System.out.println("logImageName："+imgImageName);
        File file = new File(fileName);
        try {
            multipartFile.transferTo(file);
        } catch (IllegalStateException | IOException e) {
            e.printStackTrace();
        }

        String name = request.getParameter("name");
        String imgRelativePath = ImgPath.adPath + File.separator + imgImageName;
        String redirectionTypeStr = request.getParameter("redirectionType");
        String innerRedirectionType = request.getParameter("innerRedirectionType");
        String externalLink = request.getParameter("externalLink");

        adService.saveAd(name,imgRelativePath ,RedirectionType.valueOf(redirectionTypeStr),innerRedirectionType,externalLink);
        return "redirect:/ad.do";
    }

    @RequestMapping("/ad_toEdit.do")
    public String adToEdit(HttpServletRequest request, Model model) {
        long id = Long.parseLong(request.getParameter("id"));
        model.addAttribute("ad",adService.findAd(id));
        return "ad_edit";
    }

    @RequestMapping("/ad_edit.do")
    public String adEdit(HttpServletRequest request) {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        String imgRealPathDir = request.getSession().getServletContext().getRealPath(ImgPath.adPath);
        Util.mkDir(imgRealPathDir);

        String imgRelativePath = null;
        MultipartFile multipartFile = multipartRequest.getFile("img");
        if(!multipartFile.isEmpty()) {
            String fileName = imgRealPathDir + File.separator + multipartFile.getOriginalFilename();

            File file = new File(fileName);
            try {
                multipartFile.transferTo(file);
            } catch (IllegalStateException | IOException e) {
                e.printStackTrace();
            }
            imgRelativePath = ImgPath.adPath + File.separator + multipartFile.getOriginalFilename();
        }

        long id = Long.parseLong(request.getParameter("id"));
        String name = request.getParameter("name");
        String redirectionTypeStr = request.getParameter("redirectionType");
        String innerRedirectionType = request.getParameter("innerRedirectionType");
        String externalLink = request.getParameter("externalLink");

        adService.editAd(id,name,imgRelativePath ,RedirectionType.valueOf(redirectionTypeStr),innerRedirectionType,externalLink);
        return "redirect:/ad.do";
    }

    @RequestMapping("/ad_del")
    public String adDel(HttpServletRequest request){
        String imgRealPathDir = request.getSession().getServletContext().getRealPath(ImgPath.adPath);
        long id = Long.parseLong(request.getParameter("id"));
        adService.delAd(imgRealPathDir,id);

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
