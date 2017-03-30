package com.aifeng.service;

import com.aifeng.constant.*;
import com.aifeng.dao.AdRepository;
import com.aifeng.model.Ad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.Date;
import java.util.List;

/**
 * Created by pro on 17-3-14.
 */
@Service
public class AdService {

    private final AdRepository adRepository;

    @Autowired
    public AdService(AdRepository adRepository) {
        this.adRepository = adRepository;
    }

    @Transactional
    public void saveAd(String name, String imgPath, ReligionType religionType,
                       RedirectionType redirectionType, String innerRedirectionType,
                       String externalLink) {
        Ad ad = new Ad();
        ad.setName(name);
        ad.setImg(imgPath);
        ad.setReligionType(religionType);
        ad.setIndex((int) adRepository.count() + 1);
        setDirectionType(ad, redirectionType, innerRedirectionType, externalLink);
        adRepository.save(ad);
    }

    @Transactional
    public List<Ad> findAll(ReligionType religionType, int page) {
        int pageSize = religionType == ReligionType.OTHER ? Constants.OtherIndex : Constants.NotOtherIndex;
        String indexStr = religionType == ReligionType.OTHER ? "index" : "createTime";
        Sort sort = new Sort(Sort.Direction.DESC,indexStr);
        Pageable pageable = new PageRequest(page, pageSize, sort);
        return adRepository.findAllByReligionType(religionType, pageable).getContent();
    }

    @Transactional
    public Ad findAd(long id) {
        return adRepository.findOne(id);
    }


    //TODO 修改图片时删除原来的图片
    @Transactional
    public void editAd(long id, String name, String imgPath, ReligionType religionType,
                       RedirectionType redirectionType, String innerRedirectionType,
                       String externalLink) {
        Ad ad = adRepository.findOne(id);
        ad.setName(name);
        if (imgPath != null) {
            ad.setImg(imgPath);
        }
        ad.setReligionType(religionType);
        setDirectionType(ad, redirectionType, innerRedirectionType, externalLink);
        ad.setUpdateTime(new Date());
        adRepository.save(ad);
    }

    private void setDirectionType(Ad ad, RedirectionType redirectionType, String innerRedirectionType,
                                  String externalLink) {
        ad.setRedirectionType(redirectionType);
        if (redirectionType == RedirectionType.EXTERNAL) {
            ad.setInnerRedirectionType(InnerRedirectionType.OTHER);
            ad.setExternalLink(externalLink);
        } else if (redirectionType == RedirectionType.INNER) {
            ad.setInnerRedirectionType(InnerRedirectionType.valueOf(innerRedirectionType));
            ad.setExternalLink("");
        } else {
            ad.setInnerRedirectionType(InnerRedirectionType.OTHER);
            ad.setExternalLink("");
        }
    }

    @Transactional
    public void delAd(String imgRealPathDir, long id) {
        Ad ad = adRepository.findOne(id);
        String realPath = imgRealPathDir.substring(0, imgRealPathDir.indexOf(ImgPath.adPath)) + ad.getImg();
        new File(realPath).delete();
        System.out.println("realPath: " + realPath);
        adRepository.delete(ad);
    }
}