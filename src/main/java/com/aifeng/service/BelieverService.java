package com.aifeng.service;

import com.aifeng.constant.ImgPath;
import com.aifeng.constant.ReligionType;
import com.aifeng.dao.BelieverRepository;
import com.aifeng.model.Believer;
import com.aifeng.model.Church;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.Date;
import java.util.List;

/**
 * Created by pro on 17-3-31.
 */
@Service
public class BelieverService {

    @Autowired
    BelieverRepository believerRepository;
    @Autowired
    ChurchService churchService;
    @Autowired
    BelieverChurchService believerChurchService;


    @Transactional
    public Believer registerUser(String mobileNum, String password, String name, String IDNum,
                             String address, ReligionType religionType, long church_id) {
        Believer believer = new Believer();
        believer.setName(name);
        believer.setPassword(password);
        believer.setMobileNum(mobileNum);
        believer.setIDNum(IDNum);
        believer.setAddress(address);
        believer.setReligionType(religionType);
        believer.setCreateTime(new Date());
        believer = believerRepository.save(believer);

        Church church = churchService.findChurch(church_id);
        believerChurchService.saveChurchRepository(believer, church);
        return believer;
    }

    @Transactional
    public void saveUser(String username, String password) {
        Believer believer = new Believer();
        believer.setName(username);
        believer.setPassword(password);
        believerRepository.save(believer);
    }

    @Transactional
    public Believer findUser(long id) {
        return believerRepository.findOne(id);
    }

    @Transactional
    public Believer findUser(String username, String password) {
        Believer believer = believerRepository.findByNameAndPassword(username, password);
        return believer;
    }

    @Transactional
    public List<Believer> findAll() {
        //TODO 'index'是否合理
        Sort sort = new Sort(Sort.Direction.DESC, "index");
        return believerRepository.findAll(sort);
    }

    //TODO 修改图片时删除原来的图片
    //TODO 是否要修改原来的姓名
    @Transactional
    public void editUser(long id, String name, String password, String photo,
                         String mobileNum, String IDNum, String address, ReligionType religionType) {
        Believer believer = believerRepository.findOne(id);
        believer.config(name, password, photo, mobileNum, address, religionType);
        believer.setIDNum(IDNum);
    }

    @Transactional
    public void delUser(String imgRealPathDir, long id) {
        Believer believer = believerRepository.findOne(id);
        String realPath = imgRealPathDir.substring(0, imgRealPathDir.indexOf(ImgPath.userPhotoPath)) + believer.getPhoto();
        new File(realPath).delete();
        System.out.println("realPath: " + realPath);
        believerRepository.delete(believer);
    }
}