package com.aifeng.service;

import com.aifeng.constant.Constants;
import com.aifeng.constant.ContentType;
import com.aifeng.constant.ImgPath;
import com.aifeng.constant.ReligionType;
import com.aifeng.dao.ConferenceHallRepository;
import com.aifeng.model.ConferenceHall;
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
public class ConferenceHallService {

    private final ConferenceHallRepository conferenceHallRepository;

    @Autowired
    public ConferenceHallService(ConferenceHallRepository conferenceHallRepository) {
        this.conferenceHallRepository = conferenceHallRepository;
    }

    @Transactional
    public void saveConferenceHall(String name, String address, String imgPath, ReligionType religionType, String externalLink) {
        ConferenceHall conferenceHall = new ConferenceHall();
        conferenceHall.setName(name);
        conferenceHall.setAddress(address);
        conferenceHall.setImg(imgPath);
        conferenceHall.setReligionType(religionType);
        conferenceHall.setExternalLink(externalLink);
        conferenceHall.setCreateTime(new Date());
        conferenceHallRepository.save(conferenceHall);
    }

    @Transactional
    public List<ConferenceHall> findAll(ContentType contentType, int page) {
        int pageSize = contentType == ContentType.index ? 4 : Constants.NotMobileIndex;
        Sort sort = new Sort(Sort.Direction.DESC, "createTime");
        Pageable pageRequest = new PageRequest(page, pageSize, sort);
        return conferenceHallRepository.findAll(pageRequest).getContent();
    }

    @Transactional
    public ConferenceHall findConferenceHall(long id) {
        return conferenceHallRepository.findOne(id);
    }


    //TODO 修改图片时删除原来的图片
    @Transactional
    public void editConferenceHall(long id, String name, String address, String imgPath, ReligionType religionType, String externalLink) {
        ConferenceHall conferenceHall = conferenceHallRepository.findOne(id);
        conferenceHall.setName(name);
        conferenceHall.setAddress(address);
        if (imgPath != null) {
            conferenceHall.setImg(imgPath);
        }
        conferenceHall.setUpdateTime(new Date());
        conferenceHall.setReligionType(religionType);
        conferenceHall.setExternalLink(externalLink);
        conferenceHallRepository.save(conferenceHall);
    }

    @Transactional
    public void delConferenceHall(String imgRealPathDir, long id) {
        ConferenceHall conferenceHall = conferenceHallRepository.findOne(id);
        String realPath = imgRealPathDir.substring(0, imgRealPathDir.indexOf(ImgPath.conferenceHallPath)) + conferenceHall.getImg();
        new File(realPath).delete();
        System.out.println("realPath: " + realPath);
        conferenceHallRepository.delete(conferenceHall);
    }
}