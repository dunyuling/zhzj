package com.aifeng.service;

import com.aifeng.constant.ImgPath;
import com.aifeng.constant.ReligionType;
import com.aifeng.dao.BelieverRepository;
import com.aifeng.model.Believer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.List;

/**
 * Created by pro on 17-3-14.
 */
@Service
public class BelieverService {

    private final BelieverRepository believerRepository;

    @Autowired
    public BelieverService(BelieverRepository believerRepository) {
        this.believerRepository = believerRepository;
    }

    @Transactional
    public void saveBeliever(String name, String password, String photo, String mobileNum,
                             String IDNum, String address, ReligionType religionType,
                             String frequentedAddress, boolean isLeader) {
        Believer believer = new Believer();
        believer.config(name, password, photo, mobileNum, address, religionType, frequentedAddress, isLeader);
        believer.setIDNum(IDNum);
        believerRepository.save(believer);
    }

    @Transactional
    public List<Believer> findAll() {
        //TODO 'index'是否合理
        Sort sort = new Sort(Sort.Direction.DESC, "index");
        return believerRepository.findAll(sort);
    }

    @Transactional
    public Believer findBeliever(long id) {
        return believerRepository.findOne(id);
    }

    //TODO 修改图片时删除原来的图片
    //TODO 是否要修改原来的姓名
    @Transactional
    public void editBeliever(long id, String name, String password, String photo,
                             String mobileNum, String IDNum, String address, ReligionType religionType,
                             String frequentedAddress, boolean isLeader) {
        Believer believer = believerRepository.findOne(id);
        believer.config(name, password, photo, mobileNum, address, religionType, frequentedAddress, isLeader);
        believer.setIDNum(IDNum);
    }

    @Transactional
    public void delBeliever(String imgRealPathDir, long id) {
        Believer believer = believerRepository.findOne(id);
        String realPath = imgRealPathDir.substring(0, imgRealPathDir.indexOf(ImgPath.believerPhotoPath)) + believer.getPhoto();
        new File(realPath).delete();
        System.out.println("realPath: " + realPath);
        believerRepository.delete(believer);
    }
}