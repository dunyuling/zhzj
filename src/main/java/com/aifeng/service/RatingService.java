package com.aifeng.service;

import com.aifeng.constant.Constants;
import com.aifeng.constant.ContentType;
import com.aifeng.constant.RatingType;
import com.aifeng.constant.ReligionType;
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

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

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

        ratingObjService.saveRatingObj(rating, rt, Arrays.asList(ratingObjReferenceIds));
    }

    @Transactional
    public List<Rating> findAll(ContentType contentType, int page) {
        int pageSize = contentType == ContentType.index ? 4 : Constants.NotMobileIndex;
        Sort sort = new Sort(Sort.Direction.DESC, "createTime");
        Pageable pageRequest = new PageRequest(page, pageSize, sort);
        return ratingRepository.findAll(pageRequest).getContent();
    }

    @Transactional
    public Rating findRating(long id) {
        Rating rating = ratingRepository.findOne(id);
//        List<RatingObj> ratingObjList = ratingObjService.findRating(rating);
        List<RatingObj> ratingObjList = new ArrayList<>();

        switch (rating.getRt()) {
            case 会场:
                List<BigInteger> conferenceIds = ratingObjService.findConferenceIds(rating.getId());
                for(BigInteger conferenceId : conferenceIds) {
                    ConferenceHall conferenceHall = conferenceHallService.findConferenceHall(conferenceId.longValue());
                    RatingObj ratingObj = ratingObjService.findConferenceRatingObj(conferenceHall);
                    ratingObj.setConferenceHall(conferenceHall);
                    ratingObjList.add(ratingObj);
                }
                break;
            case 人员:
                break;
        }
        rating.setRatingObjList(ratingObjList);
//        for (RatingObj ratingObj : ratingObjList) {
//            ConferenceHall conferenceHall = conferenceHallService.findConferenceHallRatingObj(ratingObj);
//            ratingObj.setConferenceHall(conferenceHall);
//        }
//        rating.setRatingObjList(ratingObjList);
        return rating;
    }

    //TODO 修改图片时删除原来的图片
    @Transactional
    public void editRating(long id, String name, String imgPath, String content,
                           ReligionType religionType, RatingType rt, Long[] ratingObjReferenceIds) {
        Rating rating = ratingRepository.findOne(id);
        rating.setName(name);
        rating.setImg(imgPath);
        rating.setContent(content);
        rating.setReligionType(religionType);
        RatingType lastRt = rating.getRt();
        rating.setRt(rt);
        rating = ratingRepository.save(rating);

        ratingObjService.editRatingObj(rating, lastRt, rt, ratingObjReferenceIds);
    }

    //TODO 待完善
    @Transactional
    public void delRating(String imgRealPathDir, long id) {

    }
}