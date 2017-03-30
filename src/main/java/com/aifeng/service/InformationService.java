package com.aifeng.service;

import com.aifeng.constant.ImgPath;
import com.aifeng.constant.InformationPublisher;
import com.aifeng.constant.VerifyStatus;
import com.aifeng.dao.InformationRepository;
import com.aifeng.model.Information;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.Date;
import java.util.List;

/**
 * Created by pro on 17-3-17.
 */
@Service
public class InformationService {

    private final InformationRepository informationRepository;

    @Autowired
    public InformationService(InformationRepository informationRepository) {
        this.informationRepository = informationRepository;
    }

    @Transactional
    public List<Information> findAll(InformationPublisher ip) {
        Sort sort = new Sort(Sort.Direction.DESC, "createTime");
        return informationRepository.findAllByInformationPublisher(ip, sort);
    }

    @Transactional
    public List<Information> findAll(InformationPublisher ip, VerifyStatus verifyStatus) {
        Sort sort = new Sort(Sort.Direction.DESC, "createTime");
        return informationRepository.findAllByInformationPublisherAndVerifyStatus(ip, verifyStatus, sort);
    }

    @Transactional
    public void saveInformation(String title, String imgPath, String content, InformationPublisher informationPublisher) {
        Information information = new Information();
        information.setTitle(title);
        information.setImg(imgPath);
        information.setContent(content);
        information.setVerifyStatus(VerifyStatus.PENDINGAUDIT);
        information.setInformationPublisher(informationPublisher);
        information.setCreateTime(new Date());
        informationRepository.save(information);
    }

    @Transactional
    public Information findInformation(long id) {
        return informationRepository.findOne(id);
    }

    @Transactional
    public void editInformation(long id, String title, String content, String imgPath) {
        Information information = informationRepository.findOne(id);
        information.setTitle(title);
        information.setContent(content);
        if (imgPath != null) {
            information.setImg(imgPath);
        }
        information.setVerifyStatus(VerifyStatus.PENDINGAUDIT);
        information.setDenyReason(null);
        information.setUpdateTime(new Date());
        informationRepository.save(information);
    }

    @Transactional
    public void verifyInformation(long id, VerifyStatus verifyStatus, String denyReason) {
        Information information = informationRepository.findOne(id);
        information.setVerifyStatus(verifyStatus);
        if (verifyStatus == VerifyStatus.AUDITTHROUGH) {
            information.setDenyReason(denyReason);
        } else {
            information.setDenyReason(null);
            information.setPublishTime(new Date());
        }
        informationRepository.save(information);
    }

    //TODO 给文件重新命名下，防止两条记录上传同样文件，删除时一并被删除
    @Transactional
    public void delInformation(long id, String imgRealPathDir) {
        Information information = informationRepository.findOne(id);
        String realPath = imgRealPathDir.substring(0, imgRealPathDir.indexOf(ImgPath.informationPath)) + information.getImg();
        new File(realPath).delete();
        informationRepository.delete(information);
    }

    @Transactional
    public void saveInformation(Information information) {
        informationRepository.save(information);
    }
}