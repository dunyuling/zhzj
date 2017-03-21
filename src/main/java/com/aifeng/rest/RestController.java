package com.aifeng.rest;

import com.aifeng.constant.InformationPublisher;
import com.aifeng.constant.VerifyStatus;
import com.aifeng.model.Information;
import com.aifeng.response.AdResponse;
import com.aifeng.response.InformationResponse;
import com.aifeng.service.AdService;
import com.aifeng.service.InformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

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

    @RequestMapping(value="/ad.json",method = RequestMethod.POST)
    public
    @ResponseBody
    AdResponse ad() {
        AdResponse adResponse = new AdResponse();
        try {
            adResponse.config("success", 1);
            adResponse.setData(adService.findAll());
        } catch (Exception e) {
            adResponse.config("failure", 0);
        }
        return adResponse;
    }

    @RequestMapping(value = "/information.json",method = RequestMethod.POST)
    public @ResponseBody
    InformationResponse information(HttpServletRequest request) {
        InformationResponse informationResponse = new InformationResponse();
        try {
            String ip = request.getParameter("ip");
            InformationPublisher informationPublisher = InformationPublisher.getIP(ip);
            informationResponse.setMsg("success");
            informationResponse.setData(informationService.findAll(informationPublisher, VerifyStatus.审核通过));
        } catch (Exception e) {
            informationResponse.setMsg("failure");
        }
        return informationResponse;
    }

    @RequestMapping(value = "/information_visit.json", method = RequestMethod.POST)
    public @ResponseBody void informationVisit(HttpServletRequest request) {
        try {
            long id = Long.parseLong(request.getParameter("id"));
            Information information = informationService.findInformation(id);
            information.setVisitTimes(information.getVisitTimes() + 1);
            informationService.saveInformation(information);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}