package com.aifeng.dao;

import com.aifeng.model.Believer;
import com.aifeng.model.ConferenceHall;
import com.aifeng.model.Rating;
import com.aifeng.model.RatingObj;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository
public interface RatingObjRepository extends JpaRepository<RatingObj, Long> {
    List<RatingObj> findAll(Sort sort);

    @Query(nativeQuery = true, value = "select believer_id from ratingobj where rating_id = ?1")
    List<BigInteger> findBelieverIds(long id);

    @Query(nativeQuery = true, value = "select conferencehall_id from ratingobj where rating_id = ?1")
    List<BigInteger> findConferenceHallIds(long id);

    List<RatingObj> findByRating(Rating rating);

    @Query(nativeQuery = true, value="select id from ratingobj where rating_id = ?1")
    List<BigInteger> findIdByRating(Rating rating);

    RatingObj findByConferenceHall(ConferenceHall conferenceHall);
    RatingObj findByBeliever(Believer believer);
}