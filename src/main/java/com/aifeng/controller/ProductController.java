package com.aifeng.controller;

import com.aifeng.Util;
import com.aifeng.constant.ContentType;
import com.aifeng.constant.ImgPath;
import com.aifeng.constant.ReligionType;
import com.aifeng.model.Product;
import com.aifeng.service.ProductService;
import com.aifeng.service.ProductSlideService;
import com.sun.xml.internal.bind.v2.runtime.output.SAXOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by pro on 17-3-21.
 */
@Controller
public class ProductController {

    private final
    ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @RequestMapping("/product.do")
    public String product(Model model) {
        //TODO 具体分页数据待指定
        model.addAttribute("products", productService.findAll(0));
        return "product";
    }

    @RequestMapping("/product_toAdd.do")
    public String productToAdd() {
        return "product_add";
    }

    @RequestMapping("/product_add.do")
    public String productAdd(HttpServletRequest request) {
        try {
            String imgPath = Util.uploadImg(request, ImgPath.productPath);
            String name = request.getParameter("name");
            ReligionType religionType = ReligionType.valueOf(request.getParameter("religionType"));
            float price = Float.parseFloat(request.getParameter("price"));
            String seller = request.getParameter("seller");
            String telephone = request.getParameter("telephone");
            String[] imgSlidePaths = Util.uploadImgs(request, ImgPath.productSlidePath);
            String intro = request.getParameter("remark");

            productService.saveProduct(name, religionType, imgPath, price, seller, telephone, imgSlidePaths, intro);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/product.do";
    }

    @RequestMapping("/product_toEdit.do")
    public String productToEdit(HttpServletRequest request, Model model) {
        try {
            long id = Long.parseLong(request.getParameter("id"));
            Product product = productService.findProduct(id);
            model.addAttribute("product", product);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "product_edit";
    }

    @RequestMapping("/product_edit.do")
    public String productEdit(HttpServletRequest request) {
        try {
            long id = Long.parseLong(request.getParameter("id"));
            String imgPath = Util.editImg(request, ImgPath.productPath);
            String name = request.getParameter("name");
            ReligionType religionType = ReligionType.valueOf(request.getParameter("religionType"));
            float price = Float.parseFloat(request.getParameter("price"));
            String seller = request.getParameter("seller");
            String telephone = request.getParameter("telephone");

            String[] imgSlidePaths = Util.editImgs(request, ImgPath.productSlidePath);
            String[] productSlideIds = request.getParameterValues("product_slide_id");

            long introId = Long.parseLong(request.getParameter("intro_id"));
            String intro = request.getParameter("remark");
            productService.editProduct(id, name, religionType, imgPath, price, seller, telephone, imgSlidePaths, productSlideIds, introId, intro);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/product.do";
    }

    @RequestMapping("/product_del.do")
    public String productDel(HttpServletRequest request) {
        String imgRealPathDir = request.getSession().getServletContext().getRealPath(ImgPath.productPath);
        long id = Long.parseLong(request.getParameter("id"));
        productService.delProduct(imgRealPathDir, id);
        return "redirect:/product.do";
    }
}
