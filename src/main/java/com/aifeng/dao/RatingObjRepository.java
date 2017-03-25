package com.aifeng.dao;

import com.aifeng.model.RatingObj;
import org.jboss.logging.annotations.Param;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingObjRepository extends JpaRepository<RatingObj, Long> {
    List<RatingObj> findAll(Sort sort);

    @Query(nativeQuery = true, value = "select believer_id from ratingobj where rating_id = ?1")
    List<Long> findBelieverIds(long id);

    @Query(nativeQuery = true, value = "select conferencehall_id from ratingobj where rating_id = ?1")
    List<Long> findConferenceHallIds(long id);
}