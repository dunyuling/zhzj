package com.aifeng.dao;

import com.aifeng.model.RatingObj;
import com.aifeng.model.RatingResult;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RatingResultRepository extends JpaRepository<RatingResult, Long> {
    RatingResult findByRatingObj(RatingObj ratingObj);
}