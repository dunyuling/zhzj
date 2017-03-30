package com.aifeng.service;

import com.aifeng.constant.Constants;
import com.aifeng.constant.ImgPath;
import com.aifeng.constant.ReligionType;
import com.aifeng.dao.CreedRepository;
import com.aifeng.model.Creed;
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
public class CreedService {

    private final CreedRepository creedRepository;

    @Autowired
    public CreedService(CreedRepository creedRepository) {
        this.creedRepository = creedRepository;
    }

    @Transactional
    public void saveCreed(String title, String content, String imgPath, ReligionType religionType) {
        Creed creed = new Creed();
        creed.setTitle(title);
        creed.setContent(content);
        creed.setImg(imgPath);
        creed.setReligionType(religionType);
        creed.setCreateTime(new Date());
        creedRepository.save(creed);
    }

    @Transactional
    public List<Creed> findAll(ReligionType religionType,int page) {
        int pageSize = religionType == ReligionType.OTHER ? 4 : Constants.NotOtherIndex;
        Sort sort = new Sort(Sort.Direction.DESC, "createTime");
        Pageable pageRequest = new PageRequest(page, pageSize, sort);

        if(religionType == ReligionType.OTHER) {
            return creedRepository.findAll(pageRequest).getContent();
        } else {
            return creedRepository.findAllByReligionType(pageRequest,religionType).getContent();
        }
    }

    @Transactional
    public Creed findCreed(long id) {
        return creedRepository.findOne(id);
    }


    //TODO 修改图片时删除原来的图片
    @Transactional
    public void editCreed(long id, String title, String content, String imgPath, ReligionType religionType) {
        Creed creed = creedRepository.findOne(id);
        creed.setTitle(title);
        if (imgPath != null) {
            creed.setImg(imgPath);
        }
        creed.setContent(content);
        creed.setReligionType(religionType);
        creed.setUpdateTime(new Date());
        creedRepository.save(creed);
    }

    @Transactional
    public ReligionType delCreed(String imgRealPathDir, long id) {
        Creed creed = creedRepository.findOne(id);
        String realPath = imgRealPathDir.substring(0, imgRealPathDir.indexOf(ImgPath.creedPath)) + creed.getImg();
        new File(realPath).delete();
        creedRepository.delete(creed);
        return creed.getReligionType();
    }
}