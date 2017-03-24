package com.aifeng.service;

import com.aifeng.constant.ImgPath;
import com.aifeng.constant.InnerRedirectionType;
import com.aifeng.constant.RedirectionType;
import com.aifeng.dao.AdRepository;
import com.aifeng.model.Ad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.function.LongToIntFunction;

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
    public void saveAd(String name, String imgPath, RedirectionType redirectionType, String innerRedirectionType,
                       String externalLink) {
        Ad ad = new Ad();
        ad.setName(name);
        ad.setImg(imgPath);
        ad.setIndex((int) adRepository.count() + 1);
        setDirectionType(ad, redirectionType, innerRedirectionType, externalLink);
        adRepository.save(ad);
    }

    @Transactional
    public List<Ad> findAll() {
        Sort sort = new Sort(Sort.Direction.DESC, "index");
        return adRepository.findAll(sort);
    }

    @Transactional
    public Ad findAd(long id) {
        return adRepository.findOne(id);
    }


    //TODO 修改图片时删除原来的图片
    @Transactional
    public void editAd(long id, String name, String imgPath, RedirectionType redirectionType, String innerRedirectionType,
                       String externalLink) {
        Ad ad = adRepository.findOne(id);
        ad.setName(name);
        if (imgPath != null) {
            ad.setImg(imgPath);
        }
        setDirectionType(ad, redirectionType, innerRedirectionType, externalLink);
        ad.setUpdateTime(new Date());
        adRepository.save(ad);
    }

    private void setDirectionType(Ad ad, RedirectionType redirectionType, String innerRedirectionType,
                                  String externalLink) {
        ad.setRedirectionType(redirectionType);
        if (redirectionType == RedirectionType.外部跳转) {
            ad.setInnerRedirectionType(null);
            ad.setExternalLink(externalLink);
        } else if (redirectionType == RedirectionType.内部跳转) {
            ad.setInnerRedirectionType(InnerRedirectionType.valueOf(innerRedirectionType));
            ad.setExternalLink(null);
        } else {
            ad.setInnerRedirectionType(null);
            ad.setExternalLink(null);
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