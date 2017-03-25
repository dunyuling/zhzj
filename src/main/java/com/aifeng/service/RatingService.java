package com.aifeng.service;

import com.aifeng.constant.*;
import com.aifeng.dao.RatingRepository;
import com.aifeng.model.Ad;
import com.aifeng.model.Rating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
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
    public RatingService(RatingRepository ratingRepository, RatingObjService ratingObjService) {
        this.ratingRepository = ratingRepository;
        this.ratingObjService = ratingObjService;
    }

    private final
    RatingObjService ratingObjService;

    @Transactional
    public void saveRating(String name, String imgPath, String content, RatingType rt, Long[] ratingObjReferenceIds) {
        Rating rating = new Rating();
        rating.setName(name);
        rating.setContent(content);
        rating.setImg(imgPath);
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
        return ratingRepository.findOne(id);
    }

    //TODO 修改图片时删除原来的图片
    @Transactional
    public void editRating(long id, String name, String imgPath, String content, RatingType rt, Long[] ratingObjReferenceIds) {
        Rating rating = ratingRepository.findOne(id);
        rating.setName(name);
        rating.setImg(imgPath);
        rating.setContent(content);
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