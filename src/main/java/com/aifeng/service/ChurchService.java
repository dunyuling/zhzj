package com.aifeng.service;

import com.aifeng.constant.Constants;
import com.aifeng.constant.ReligionType;
import com.aifeng.constant.VerifyStatus;
import com.aifeng.dao.ChurchRepository;
import com.aifeng.model.Church;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by pro on 17-3-14.
 */
@Service
public class ChurchService {

    private final ChurchRepository churchRepository;

    @Autowired
    public ChurchService(ChurchRepository churchRepository) {
        this.churchRepository = churchRepository;
    }

    @Transactional
    public void saveChurch(String name, String address, String telephone, ReligionType religionType) {
        Church church = new Church();
        church.setName(name);
        church.setAddress(address);
        church.setTelephone(telephone);
        church.setResponsiblePerson("");
        church.setReligionType(religionType);
        church.setCreateTime(new Date());
        churchRepository.save(church);
    }

    @Transactional
    public List<Church> findAll(ReligionType religionType, VerifyStatus verifyStatus, int page) {
        int pageSize = religionType == ReligionType.OTHER ? Constants.OtherIndex : Constants.NotOtherIndex;
        String indexStr = religionType == ReligionType.OTHER ? "index" : "createTime";
        Sort sort = new Sort(Sort.Direction.DESC, indexStr);
        Pageable pageable = new PageRequest(page, pageSize, sort);
        List<Church> churchList = churchRepository.findAllByReligionTypeAndVerifyStatus(religionType, verifyStatus, pageable).getContent();
        churchList.forEach(church -> church.setBelieverChurchSet(null));
        return churchList;
    }

    @Transactional
    public Church findChurch(long id) {
        return churchRepository.findOne(id);
    }


    //TODO 修改图片时删除原来的图片
    @Transactional
    public void editChurch(long id, String name, String address, /*ReligionType religionType,*/
                           String telephone) {
        //不允许修改教会所属宗教
        Church church = churchRepository.findOne(id);
        church.setName(name);
        church.setTelephone(telephone);
        church.setAddress(address);
        church.setUpdateTime(new Date());
        churchRepository.save(church);
    }

    @Transactional
    public void delChurch(/*String imgRealPathDir, */long id) {
        Church church = churchRepository.findOne(id);
//        String realPath = imgRealPathDir.substring(0, imgRealPathDir.indexOf(ImgPath.adPath)) + church.getImg();
//        new File(realPath).delete();
//        System.out.println("realPath: " + realPath);
        churchRepository.delete(church);
    }
}