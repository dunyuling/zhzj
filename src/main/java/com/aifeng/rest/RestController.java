package com.aifeng.rest;

import com.aifeng.constant.ContentType;
import com.aifeng.constant.InformationPublisher;
import com.aifeng.constant.VerifyStatus;
import com.aifeng.model.ConferenceHall;
import com.aifeng.model.Information;
import com.aifeng.model.Product;
import com.aifeng.response.*;
import com.aifeng.service.AdService;
import com.aifeng.service.ConferenceHallService;
import com.aifeng.service.InformationService;
import com.aifeng.service.ProductService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    private ConferenceHallService conferenceHallService;

    @Autowired
    public void setConferenceHallService(ConferenceHallService conferenceHallService) {
        this.conferenceHallService = conferenceHallService;
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
            String ip = getParamMap(request).get("ip");
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
            Map<String, String> paramMap = getParamMap(request);
            long id = Long.parseLong(paramMap.get("id"));

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
            Map<String, String> paramMap = getParamMap(request);
            ContentType contentType = ContentType.valueOf(paramMap.get("type"));
            int page = Integer.parseInt(paramMap.get("page"));
            List<Product> productList = productService.findAllFromMobile(contentType, page);
            productResponse.config(1, "success", productList);
        } catch (Exception e) {
            e.printStackTrace();
            productResponse.config(0, "failure", new ArrayList<>());
        }
        return productResponse;
    }

    @RequestMapping(value = "/product_detail.json", method = RequestMethod.POST)
    public
    @ResponseBody
    ProductResponse productDetail(HttpServletRequest request) {
        ProductResponse productDetailResponse = new ProductResponse();
        try {
            Map<String, String> paramMap = getParamMap(request);
            long id = Long.parseLong(paramMap.get("id"));
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

    //TODO  分页时需给客户端总数
    @RequestMapping(value = "/conferenceHall.json", method = RequestMethod.POST)
    public
    @ResponseBody
    ConferenceHallResponse conferenceHall(HttpServletRequest request) {
        ConferenceHallResponse conferenceHallResponse = new ConferenceHallResponse();
        try {
            Map<String, String> paramMap = getParamMap(request);
            int page = Integer.parseInt(paramMap.get("page"));
            ContentType contentType = ContentType.valueOf(paramMap.get("type"));
            List<ConferenceHall> conferenceHallList = conferenceHallService.findAll(contentType, page);
            conferenceHallResponse.config(1, "success", conferenceHallList);
        } catch (Exception e) {
            e.printStackTrace();
            conferenceHallResponse.config(0, "failure", null);
        }
        return conferenceHallResponse;
    }

    private Map<String, String> getParamMap(HttpServletRequest request) {
        String data = request.getParameter("data");
        return jsonStringToMap(data);
    }

    private Map<String, String> jsonStringToMap(String json) {
        Map<String, String> map = new HashMap<>();
        try {
            ObjectMapper mapper = new ObjectMapper();
            // convert JSON string to Map
            map = mapper.readValue(json, new TypeReference<Map<String, String>>() {
            });
            System.out.println(map);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }
}