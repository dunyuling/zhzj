package com.aifeng.dao;

import com.aifeng.constant.ReligionType;
import com.aifeng.model.Ad;
import com.aifeng.model.Manager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdRepository extends JpaRepository<Ad, Long> {
    List<Ad> findAll(Sort sort);

    Page<Ad> findAllByReligionType(ReligionType religionType, Pageable pageable);
}