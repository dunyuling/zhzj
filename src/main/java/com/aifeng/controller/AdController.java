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
import org.springframework.web.multipart.MultipartRequest;
import org.springframework.web.multipart.MultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.awt.image.ImageProducer;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

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
        String logoRealPathDir = request.getSession().getServletContext().getRealPath(ImgPath.adPath);
        Util.mkDir(logoRealPathDir);

        MultipartFile multipartFile = multipartRequest.getFile("img");
//        String suffix = multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf("."));
//        String logImageName = UUID.randomUUID().toString() + suffix;// 构建文件名称
        String logImageName = multipartFile.getOriginalFilename();
        String fileName = logoRealPathDir + File.separator + logImageName;

        System.out.println("logImageName："+logImageName);
        File file = new File(fileName);
        try {
            multipartFile.transferTo(file);
        } catch (IllegalStateException | IOException e) {
            e.printStackTrace();
        }

        String name = request.getParameter("name");
        String imgRelativePath = ImgPath.adPath + File.separator + logImageName;
        String redirectionType = request.getParameter("redirectionType");

        adService.saveAd(name,imgRelativePath ,RedirectionType.valueOf(redirectionType));
        return "ad";
    }

    @RequestMapping("/ad_toEdit.do")
    public String adToEdit() {
        return "ad_edit";
    }

    @RequestMapping("/ad_del")
    public String adDel(){
        return "ad";
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
