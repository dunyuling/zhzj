package com.aifeng.rest;

import com.aifeng.constant.ContentType;
import com.aifeng.constant.InformationPublisher;
import com.aifeng.constant.VerifyStatus;
import com.aifeng.model.Information;
import com.aifeng.model.Product;
import com.aifeng.response.AdResponse;
import com.aifeng.response.InformationResponse;
import com.aifeng.response.ProductDetailResponse;
import com.aifeng.response.ProductResponse;
import com.aifeng.service.AdService;
import com.aifeng.service.InformationService;
import com.aifeng.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pro on 17-3-14.
 */
@Controller
@RequestMapping("/mobile")
public class RestController {

    private AdService adService;

    @Autowired
    public void setAdService(AdService adService) {
        this.adService = adService;
    }

    private InformationService informationService;

    @Autowired
    public void setInformationService(InformationService informationService) {
        this.informationService = informationService;
    }

    private
    ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping(value = "/ad.json", method = RequestMethod.POST)
    public
    @ResponseBody
    AdResponse ad() {
        AdResponse adResponse = new AdResponse();
        try {
            adResponse.config(1, "success", adService.findAll());
        } catch (Exception e) {
            e.printStackTrace();
            adResponse.config(0, "failure", null);
        }
        return adResponse;
    }

    @RequestMapping(value = "/information.json", method = RequestMethod.POST)
    public
    @ResponseBody
    InformationResponse information(HttpServletRequest request) {
        InformationResponse informationResponse = new InformationResponse();
        try {
            String ip = request.getParameter("ip");
            InformationPublisher informationPublisher = InformationPublisher.getIP(ip);
            informationResponse.config(1, "success", informationService.findAll(informationPublisher, VerifyStatus.审核通过));
        } catch (Exception e) {
            e.printStackTrace();
            informationResponse.config(0, "failure", null);
        }
        return informationResponse;
    }

    @RequestMapping(value = "/information_visit.json", method = RequestMethod.POST)
    public
    @ResponseBody
    void informationVisit(HttpServletRequest request) {
        try {
            long id = Long.parseLong(request.getParameter("id"));
            Information information = informationService.findInformation(id);
            information.setVisitTimes(information.getVisitTimes() + 1);
            informationService.saveInformation(information);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/product.json", method = RequestMethod.POST)
    public
    @ResponseBody
    ProductResponse product(HttpServletRequest request) {
        ProductResponse productResponse = new ProductResponse();
        try {
            ContentType contentType = ContentType.valueOf(request.getParameter("type"));
            int page = Integer.parseInt(request.getParameter("page"));
            List<Product> productList = productService.findAllFromMobile(contentType, page);
            productResponse.config(1, "success", productList);
        } catch (Exception e) {
            e.printStackTrace();
            productResponse.config(0, "failure", new ArrayList<>());
        }
        return productResponse;
    }

    @RequestMapping(value = "/product_detail", method = RequestMethod.POST)
    public
    @ResponseBody
    ProductDetailResponse productDetail(HttpServletRequest request) {
        ProductDetailResponse productDetailResponse = new ProductDetailResponse();
        try {
            long id = Long.parseLong(request.getParameter("id"));
            Product product = productService.findProduct(id);

            List<Product> productList = new ArrayList<>();
            productList.add(product);

            productDetailResponse.config(1, "success", productList);
        } catch (Exception e) {
            e.printStackTrace();
            productDetailResponse.config(0, "failure", null);
        }
        return productDetailResponse;
    }
}