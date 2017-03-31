package com.aifeng.rest;

import com.aifeng.constant.ContentType;
import com.aifeng.constant.InformationPublisher;
import com.aifeng.constant.ReligionType;
import com.aifeng.constant.VerifyStatus;
import com.aifeng.model.*;
import com.aifeng.response.*;
import com.aifeng.service.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.dialect.PostgreSQL82Dialect;
import org.omg.PortableServer.POA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
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
    public RestController(RatingService ratingService, CreedService creedService, ScriptureService scriptureService, ChurchService churchService) {
        this.ratingService = ratingService;
        this.creedService = creedService;
        this.scriptureService = scriptureService;
        this.churchService = churchService;
    }

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

    private final
    RatingService ratingService;

    private final ChurchService churchService;

    @Autowired
    public void setConferenceHallService(ConferenceHallService conferenceHallService) {
        this.conferenceHallService = conferenceHallService;
    }

    private final CreedService creedService;

    private final ScriptureService scriptureService;

    @RequestMapping(value = "/ad.json", method = RequestMethod.POST)
    public
    @ResponseBody
    Response<List<Ad>> ad(HttpServletRequest request) {
        Response<List<Ad>> adResponse = new Response<>();
        try {
            Map<String, String> paramMap = getParamMap(request);
            ReligionType religionType = ReligionType.valueOf(paramMap.get("rt"));
            int page = Integer.parseInt(paramMap.get("page"));

            adResponse.config(1, "success", adService.findAll(religionType, page));
        } catch (Exception e) {
            e.printStackTrace();
            adResponse.config(0, "failure", null);
        }
        return adResponse;
    }

    @RequestMapping(value = "/information.json", method = RequestMethod.POST)
    public
    @ResponseBody
    Response<List<Information>> information(HttpServletRequest request) {
        Response<List<Information>> informationResponse = new Response<>();
        try {
            String ip = getParamMap(request).get("ip");
            InformationPublisher informationPublisher = InformationPublisher.valueOf(ip);
            informationResponse.config(1, "success", informationService.findAll(informationPublisher, VerifyStatus.AUDITTHROUGH));
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
    Response<List<Product>> product(HttpServletRequest request) {
        Response<List<Product>> productResponse = new Response<>();
        try {
            Map<String, String> paramMap = getParamMap(request);

            ReligionType religionType = ReligionType.valueOf(paramMap.get("rt"));
            int page = Integer.parseInt(paramMap.get("page"));
            List<Product> productList = productService.findAllFromMobile(religionType, page);
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
    Response<Product> productDetail(HttpServletRequest request) {
        Response<Product> productDetailResponse = new Response<>();
        try {
            Map<String, String> paramMap = getParamMap(request);
            long id = Long.parseLong(paramMap.get("id"));
            Product product = productService.findProduct(id);

            productDetailResponse.config(1, "success", product);
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
    Response<List<ConferenceHall>> conferenceHall(HttpServletRequest request) {
        Response<List<ConferenceHall>> conferenceHallResponse = new Response<>();
        try {
            Map<String, String> paramMap = getParamMap(request);
            int page = Integer.parseInt(paramMap.get("page"));
            ReligionType religionType = ReligionType.valueOf(paramMap.get("rt"));
            List<ConferenceHall> conferenceHallList = conferenceHallService.findAll(religionType, page);
            conferenceHallResponse.config(1, "success", conferenceHallList);
        } catch (Exception e) {
            e.printStackTrace();
            conferenceHallResponse.config(0, "failure", null);
        }
        return conferenceHallResponse;
    }

    @RequestMapping(value = "/rating.json", method = RequestMethod.POST)
    public
    @ResponseBody
    Response<List<Rating>> rating(HttpServletRequest request) {
        Response<List<Rating>> ratingResponse = new Response<>();
        try {
            Map<String, String> paramMap = getParamMap(request);
            int page = Integer.parseInt(paramMap.get("page"));
            ReligionType religionType = ReligionType.valueOf(paramMap.get("rt"));
            List<Rating> list = ratingService.findAll(ContentType.mobile, religionType, page);
            ratingResponse.config(1, "success", list);
        } catch (Exception e) {
            e.printStackTrace();
            ratingResponse.config(0, "failure", null);
        }
        return ratingResponse;
    }

    @RequestMapping(value = "/creed.json", method = RequestMethod.POST)
    public
    @ResponseBody
    Response<List<Creed>> creed(HttpServletRequest request) {
        Response<List<Creed>> creedResponse = new Response<List<Creed>>();
        try {
            Map<String, String> paramMap = getParamMap(request);
            int page = Integer.parseInt(paramMap.get("page"));
            ReligionType religionType = ReligionType.valueOf(paramMap.get("rt"));
            List<Creed> list = creedService.findAll(religionType, page);
            creedResponse.config(1, "success", list);
        } catch (Exception e) {
            e.printStackTrace();
            creedResponse.config(0, "failure", null);
        }
        return creedResponse;
    }

    @RequestMapping(value = "/scripture.json", method = RequestMethod.POST)
    public
    @ResponseBody
    Response<List<Scripture>> scripture(HttpServletRequest request) {
        Response<List<Scripture>> scriptureResponse = new Response<>();
        try {
            Map<String, String> paramMap = getParamMap(request);
            int page = Integer.parseInt(paramMap.get("page"));
            ReligionType religionType = ReligionType.valueOf(paramMap.get("rt"));
            List<Scripture> list = scriptureService.findAll(religionType, page);
            scriptureResponse.config(1, "success", list);
        } catch (Exception e) {
            e.printStackTrace();
            scriptureResponse.config(0, "failure", null);
        }
        return scriptureResponse;
    }

    @RequestMapping(value = "/register_church.json", method = RequestMethod.POST)
    public
    @ResponseBody
    Response<List<Church>> registerChurch(HttpServletRequest request) {
        Response<List<Church>> churchResponse = new Response<>();
        try {
            Map<String, String> paramMap = getParamMap(request);
            int page = Integer.parseInt(paramMap.get("page"));
            ReligionType religionType = ReligionType.valueOf(paramMap.get("rt"));
            List<Church> list = churchService.findAll(religionType, VerifyStatus.AUDITTHROUGH, page);
            churchResponse.config(1, "success", list);
        } catch (Exception e) {
            e.printStackTrace();
            churchResponse.config(0, "failure", null);
        }
        return churchResponse;
    }

    @RequestMapping(value = "/register.json", method = RequestMethod.POST)
    public
    @ResponseBody
    Response register(Believer believer) {

        return null;
    }

    @RequestMapping(value = "/edit_information.json", method = RequestMethod.POST)
    public
    @ResponseBody
    Response editInformation(HttpServletRequest request) {

        return null;
    }

    @RequestMapping(value = "/edit_pwd.json", method = RequestMethod.POST)
    public
    @ResponseBody
    Response editPwd(HttpServletRequest request) {

        return null;
    }

    @RequestMapping(value = "/create_church.json", method = RequestMethod.POST)
    public
    @ResponseBody
    Response createChurch(HttpServletRequest request) {

        return null;
    }

    @RequestMapping(value = "/audit_church.json", method = RequestMethod.POST)
    public
    @ResponseBody
    Response auditChurch(HttpServletRequest httpServletRequest) {

        return null;
    }

    @RequestMapping(value = "/create_notice.json", method = RequestMethod.POST)
    public
    @ResponseBody
    Response createNotice(HttpServletRequest request) {

        return null;
    }

    @RequestMapping(value = "/audit_notice.json", method = RequestMethod.POST)
    public
    @ResponseBody
    Response auditNotice(HttpServletRequest request) {

        return null;
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