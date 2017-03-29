package com.aifeng.service;

import com.aifeng.constant.Constants;
import com.aifeng.constant.ImgPath;
import com.aifeng.constant.ReligionType;
import com.aifeng.dao.CreedRepository;
import com.aifeng.dao.ScriptureRepository;
import com.aifeng.model.Creed;
import com.aifeng.model.Scripture;
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
public class ScriptureService {

    private final ScriptureRepository scriptureRepository;

    @Autowired
    public ScriptureService(ScriptureRepository scriptureRepository) {
        this.scriptureRepository = scriptureRepository;
    }

    @Transactional
    public void saveScripture(String title, String content, String coverPath, String descImg, ReligionType religionType) {
        Scripture scripture = new Scripture();
        scripture.setTitle(title);
        scripture.setContent(content);
        scripture.setDescImg(descImg);
        scripture.setCoverImg(coverPath);
        scripture.setReligionType(religionType);
        scripture.setCreateTime(new Date());
        scriptureRepository.save(scripture);
    }

    @Transactional
    public List<Scripture> findAll(ReligionType religionType, int page) {
        int pageSize = religionType == ReligionType.其它 ? 4 : Constants.NotOtherIndex;
        Sort sort = new Sort(Sort.Direction.DESC, "createTime");
        Pageable pageRequest = new PageRequest(page, pageSize, sort);

        if (religionType == ReligionType.其它) {
            return scriptureRepository.findAll(pageRequest).getContent();
        } else {
            return scriptureRepository.findAllByReligionType(pageRequest, religionType).getContent();
        }
    }

    @Transactional
    public Scripture findScripture(long id) {
        return scriptureRepository.findOne(id);
    }


    //TODO 修改图片时删除原来的图片
    @Transactional
    public void editScripture(long id, String title, String content, String coverImg, String descImg, ReligionType religionType) {
        Scripture scripture = scriptureRepository.findOne(id);
        scripture.setTitle(title);
        if (coverImg != null) {
            scripture.setCoverImg(coverImg);
        }
        if (descImg != null) {
            scripture.setDescImg(descImg);
        }
        scripture.setContent(content);
        scripture.setReligionType(religionType);
        scripture.setUpdateTime(new Date());
        scriptureRepository.save(scripture);
    }

    @Transactional
    public ReligionType delScripture(String imgRealPathDir, long id) {
        Scripture scripture = scriptureRepository.findOne(id);
        String realCoverPath = imgRealPathDir.substring(0, imgRealPathDir.indexOf(ImgPath.scripturePath)) + scripture.getCoverImg();
        new File(realCoverPath).delete();

        String realDescPath = imgRealPathDir.substring(0, imgRealPathDir.indexOf(ImgPath.scripturePath)) + scripture.getDescImg();
        new File(realDescPath).delete();
        scriptureRepository.delete(scripture);
        return scripture.getReligionType();
    }
}