package com.aifeng.service;

import com.aifeng.constant.*;
import com.aifeng.dao.RatingRepository;
import com.aifeng.dao.RatingResultRepository;
import com.aifeng.model.Ad;
import com.aifeng.model.Rating;
import com.aifeng.model.RatingObj;
import com.aifeng.model.RatingResult;
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
public class RatingResultService {

    private final RatingResultRepository ratingResultRepository;

    @Autowired
    public RatingResultService(RatingResultRepository ratingResultRepository) {
        this.ratingResultRepository = ratingResultRepository;
    }

    //TODO 可以优化锁粒度
    @Transactional
    public synchronized void saveRatingResult(RatingObj ratingObj) {
        RatingResult ratingResult = ratingResultRepository.findByRatingObj(ratingObj);
        if (ratingResult == null) {
            ratingResult = new RatingResult();
            ratingResult.setRatingObj(ratingObj);
            ratingResult.setTicketNum(1);
        } else {
            ratingResult.setTicketNum(ratingResult.getTicketNum() + 1);
        }
        ratingResultRepository.save(ratingResult);
    }

    @Transactional
    public void delRatingResult(RatingObj ratingObj) {
        RatingResult ratingResult = ratingResultRepository.findByRatingObj(ratingObj);
        if(ratingResult != null) {
            ratingResultRepository.delete(ratingResult);
        }
    }
}