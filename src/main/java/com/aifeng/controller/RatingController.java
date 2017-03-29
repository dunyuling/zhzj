package com.aifeng.controller;

import com.aifeng.Util;
import com.aifeng.constant.ContentType;
import com.aifeng.constant.ImgPath;
import com.aifeng.constant.RatingType;
import com.aifeng.constant.ReligionType;
import com.aifeng.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by pro on 17-3-21.
 */
@Controller
public class RatingController {

    private final
    RatingService ratingService;

    @Autowired
    public RatingController(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    @RequestMapping("/rating.do")
    public String rating(HttpServletRequest request, Model model) {
        ReligionType religionType = Util.getDefaultReligionType(request);
        model.addAttribute("ratings", ratingService.findAll(ContentType.console, religionType, 0))
                .addAttribute("religionType", religionType);
        return "rating";
    }

    @RequestMapping("/rating_toAdd.do")
    public String ratingToAdd() {
        return "rating_add";
    }

    @RequestMapping("/rating_add.do")
    public String productAdd(HttpServletRequest request) {
        ReligionType religionType = ReligionType.佛教;
        try {
            String imgPath = Util.uploadImg(request, ImgPath.ratingPath);
            String name = request.getParameter("name");
            religionType = ReligionType.valueOf(request.getParameter("religionType"));
            String content = request.getParameter("remark");
            RatingType rt = RatingType.valueOf(request.getParameter("rt"));
            ratingService.saveRating(name, imgPath, content, religionType, rt, getSelectObjIds(request));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/rating.do?religionType=" + ReligionType.getConParam(religionType);
    }

    @RequestMapping("/rating_toEdit.do")
    public String productToEdit(HttpServletRequest request, Model model) {
        try {
            long id = Long.parseLong(request.getParameter("id"));
            ModelMap modelMap = ratingService.findRating(id);
            model.addAttribute("rating", modelMap.get("rating"));
            request.getSession().setAttribute("toValidIds", modelMap.get("toValidIds"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "rating_edit";
    }

    @RequestMapping("/rating_edit.do")
    public String productEdit(HttpServletRequest request) {
        ReligionType religionType = ReligionType.佛教;
        try {
            long id = Long.parseLong(request.getParameter("id"));
            String imgPath = Util.editImg(request, ImgPath.ratingPath);
            String name = request.getParameter("name");
            String content = request.getParameter("remark");
            religionType = ReligionType.valueOf(request.getParameter("religionType"));

            ratingService.editRating(id, name, imgPath, content, religionType, getSelectObjIds(request));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            request.getSession().setAttribute("toValidIds", null);
        }
        return "redirect:/rating.do?religionType=" + ReligionType.getConParam(religionType);
    }

    private Long[] getSelectObjIds(HttpServletRequest request) {
        String objIds[] = request.getParameterValues("select_obj_id");
        Long[] ids = new Long[objIds.length];
        for (int i = 0; i < objIds.length; i++) {
            ids[i] = Long.parseLong(objIds[i]);
        }
        return ids;
    }

    @RequestMapping("/rating_del.do")
    public String productDel(HttpServletRequest request) {
        String imgRealPathDir = request.getSession().getServletContext().getRealPath(ImgPath.ratingPath);
        long id = Long.parseLong(request.getParameter("id"));
        ReligionType religionType = ratingService.delRating(imgRealPathDir, id);
        return "redirect:/rating.do?religionType=" + ReligionType.getConParam(religionType);
    }
}