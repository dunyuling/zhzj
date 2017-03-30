package com.aifeng.service;

import com.aifeng.constant.*;
import com.aifeng.dao.RatingRepository;
import com.aifeng.model.ConferenceHall;
import com.aifeng.model.Rating;
import com.aifeng.model.RatingObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;

import java.io.File;
import java.math.BigInteger;
import java.util.*;

/**
 * Created by pro on 17-3-14.
 */
@Service
public class RatingService {

    private final RatingRepository ratingRepository;

    @Autowired
    public RatingService(RatingRepository ratingRepository, RatingObjService ratingObjService, ConferenceHallService conferenceHallService) {
        this.ratingRepository = ratingRepository;
        this.ratingObjService = ratingObjService;
        this.conferenceHallService = conferenceHallService;
    }

    private final
    RatingObjService ratingObjService;
    private final ConferenceHallService conferenceHallService;

    @Transactional
    public void saveRating(String name, String imgPath, String content,
                           ReligionType religionType, RatingType rt, Long[] ratingObjReferenceIds) {
        Rating rating = new Rating();
        rating.setName(name);
        rating.setContent(content);
        rating.setImg(imgPath);
        rating.setReligionType(religionType);
        rating.setRt(rt);
        rating.setCreateTime(new Date());
        rating = ratingRepository.save(rating);

        if (ratingObjReferenceIds != null) {
            ratingObjService.saveRatingObj(rating, rt, Arrays.asList(ratingObjReferenceIds));
        }
    }

    @Transactional
    public List<Rating> findAll(ContentType contentType, ReligionType religionType, int page) {
        int pageSize = religionType == ReligionType.OTHER ? 4 : Constants.NotOtherIndex;
        Sort sort = new Sort(Sort.Direction.DESC, "createTime");
        Pageable pageRequest = new PageRequest(page, pageSize, sort);
        List<Rating> ratings = ratingRepository.findAll(pageRequest).getContent();
        List<Rating> temp = new ArrayList<>();
        if (contentType != ContentType.console) {
            for (Rating rating : ratings) {
                ModelMap modelMap = findRating(rating.getId());
                rating = (Rating) modelMap.get("rating");
                temp.add(rating);
            }
            return temp;
        }
        return ratings;
    }

    @Transactional
    public ModelMap findRating(long id) {
        Rating rating = ratingRepository.findOne(id);
        List<RatingObj> ratingObjList = new ArrayList<>();
        List<Long> toValidIds = new ArrayList<>();
        ModelMap modelMap = new ModelMap();

        switch (rating.getRt()) {
            case CONFERENCEHALL:
                List<BigInteger> conferenceIds = ratingObjService.findConferenceIds(rating.getId());
                for (BigInteger conferenceId : conferenceIds) {
                    ConferenceHall conferenceHall = conferenceHallService.findConferenceHall(conferenceId.longValue());
                    RatingObj ratingObj = ratingObjService.findConferenceRatingObj(conferenceHall);
                    ratingObj.setConferenceHall(conferenceHall);
                    ratingObjList.add(ratingObj);
                    toValidIds.add(conferenceHall.getId());
                }
                break;
            case BELIEVER:
                break;
        }
        rating.setRatingObjList(ratingObjList);
        modelMap.put("rating", rating);
        modelMap.put("toValidIds", toValidIds);
        return modelMap;
    }

    //TODO 修改图片时删除原来的图片
    @Transactional
    public void editRating(long id, String name, String imgPath, String content,
                           ReligionType religionType, Long[] ratingObjReferenceIds) {
        Rating rating = ratingRepository.findOne(id);
        rating.setName(name);
        if (imgPath != null) {
            rating.setImg(imgPath);
        }
        rating.setContent(content);
        rating.setReligionType(religionType);
        rating.setUpdateTime(new Date());
        rating = ratingRepository.save(rating);

        ratingObjService.editRatingObj(rating, ratingObjReferenceIds);
    }


    @Transactional
    public ReligionType delRating(String imgRealPathDir, long id) {
        Rating rating = ratingRepository.findOne(id);
        String realPath = imgRealPathDir.substring(0, imgRealPathDir.indexOf(ImgPath.ratingPath)) + rating.getImg();
        new File(realPath).delete();

        List<BigInteger> ratingObjIds = ratingObjService.findIds(rating);
        for (BigInteger ratingObjId : ratingObjIds) {
            ratingObjService.delRatingObj(ratingObjId.longValue());
        }
        ratingRepository.delete(rating);
        return rating.getReligionType();
    }
}