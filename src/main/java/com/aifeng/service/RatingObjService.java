package com.aifeng.service;

import com.aifeng.constant.RatingType;
import com.aifeng.dao.RatingObjRepository;
import com.aifeng.model.Believer;
import com.aifeng.model.ConferenceHall;
import com.aifeng.model.Rating;
import com.aifeng.model.RatingObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * Created by pro on 17-3-14.
 */
@Service
public class RatingObjService {

    private final RatingObjRepository ratingObjRepository;

    @Autowired
    public RatingObjService(RatingObjRepository ratingObjRepository, ConferenceHallService conferenceHallService, BelieverService believerService, RatingResultService ratingResultService) {
        this.ratingObjRepository = ratingObjRepository;
        this.conferenceHallService = conferenceHallService;
        this.believerService = believerService;
        this.ratingResultService = ratingResultService;
    }

    private final ConferenceHallService conferenceHallService;
    private final BelieverService believerService;
    private final RatingResultService ratingResultService;

    @Transactional
    public void saveRatingObj(Rating rating, RatingType rt, List<Long> ratingObjReferenceIds) {
        switch (rt) {
            case 会场:
                for (long id : ratingObjReferenceIds) {
                    RatingObj ratingObj = new RatingObj();
                    ConferenceHall conferenceHall = conferenceHallService.findConferenceHall(id);
                    ratingObj.setConferenceHall(conferenceHall);
                    ratingObj.setBeliever(null);
                    ratingObj.setRating(rating);
                    ratingObjRepository.save(ratingObj);
                }
                break;
            case 人员:
                for (long id : ratingObjReferenceIds) {
                    RatingObj ratingObj = new RatingObj();
                    Believer believer = believerService.findBeliever(id);
                    ratingObj.setBeliever(believer);
                    ratingObj.setConferenceHall(null);
                    ratingObj.setRating(rating);
                    ratingObjRepository.save(ratingObj);
                }
                break;
        }
    }

    @Transactional
    public List<BigInteger> findIds(Rating rating) {
        return ratingObjRepository.findIdByRating(rating);
    }

    //TODO 修改图片时删除原来的图片
    //TODO 删除这次未被选中的
    @Transactional
    public void editRatingObj(Rating rating, Long[] ratingObjReferenceIds) {
        List<BigInteger> list = findIds(rating);

        List<Long> toAdd = new ArrayList<>();
        for (long l : ratingObjReferenceIds) {
            if (!list.contains(BigInteger.valueOf(l))) toAdd.add(l);
        }

        List<Long> temp = Arrays.asList(ratingObjReferenceIds);
        List<Long> toDel = new ArrayList<>();
        for (BigInteger l : list) {
            if (!temp.contains(l.longValue())) {
                toDel.add(l.longValue());
            }
        }

        System.out.println("---");
        if (!toAdd.isEmpty())
            saveRatingObj(rating, rating.getRt(), toAdd);

        if (!toDel.isEmpty()) {
            for (long id : toDel) {
                RatingObj ratingObj = ratingObjRepository.findOne(id);
                ratingResultService.delRatingResult(ratingObj);
                ratingObjRepository.delete(id);
            }
        }
    }

    @Transactional
    public List<BigInteger> findConferenceIds(long ratingId) {
        return ratingObjRepository.findConferenceHallIds(ratingId);
    }

    @Transactional
    public RatingObj findConferenceRatingObj(ConferenceHall conferenceHall) {
        return ratingObjRepository.findByConferenceHall(conferenceHall);
    }

    @Transactional
    public void delRatingObj(long ratingObjId) {
        RatingObj ratingObj = ratingObjRepository.findOne(ratingObjId);
        ratingResultService.delRatingResult(ratingObj);
        ratingObjRepository.delete(ratingObjId);
    }
}