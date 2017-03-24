package com.aifeng.dao;

import com.aifeng.model.ConferenceHall;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConferenceHallRepository extends JpaRepository<ConferenceHall, Long> {
    Page<ConferenceHall> findAll(Pageable pageable);
}