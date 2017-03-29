package com.aifeng.dao;

import com.aifeng.constant.ReligionType;
import com.aifeng.model.ConferenceHall;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConferenceHallRepository extends JpaRepository<ConferenceHall, Long> {
    Page<ConferenceHall> findAll(Pageable pageable);

    Page<ConferenceHall> findAllByReligionType(Pageable pageable, ReligionType religionType);
}