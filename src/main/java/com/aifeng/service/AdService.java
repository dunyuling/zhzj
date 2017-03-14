package com.aifeng.service;

import com.aifeng.constant.RedirectionType;
import com.aifeng.dao.AdRepository;
import com.aifeng.model.Ad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.function.LongToIntFunction;

/**
 * Created by pro on 17-3-14.
 */
@Service
public class AdService {

    @Autowired
    AdRepository adRepository;

    @Transactional
    public void saveAd(String name, String imgPath, RedirectionType redirectionType) {
        Ad ad = new Ad();
        ad.setName(name);
        ad.setImg(imgPath);
        ad.setRedirectionType(redirectionType);
        ad.setIndex((int)adRepository.count() + 1);
        adRepository.save(ad);
    }

    @Transactional
    public List<Ad> findAll() {
        Sort sort = new Sort(Sort.Direction.DESC,"index");
        return adRepository.findAll(sort);
    }

}
