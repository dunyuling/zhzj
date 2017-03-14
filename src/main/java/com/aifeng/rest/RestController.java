package com.aifeng.rest;

import com.aifeng.response.AdResponse;
import com.aifeng.service.AdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by pro on 17-3-14.
 */
@Controller
public class RestController {

    @Autowired
    AdService adService;

    @RequestMapping("/mobile/ad.json")
    public
    @ResponseBody
    AdResponse mobileAdd() {
        AdResponse adResponse = new AdResponse();

        try {
            adResponse.config("success", 1);
            adResponse.setAds(adService.findAll());
            return adResponse;
        } catch (Exception e) {
            adResponse.config("failure", 0);
            return adResponse;
        }

    }
}
