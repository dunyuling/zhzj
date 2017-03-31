package com.aifeng.dao;

import com.aifeng.constant.ReligionType;
import com.aifeng.constant.VerifyStatus;
import com.aifeng.model.Church;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChurchRepository extends JpaRepository<Church, Long> {
    List<Church> findAll(Sort sort);

    Page<Church> findAllByReligionTypeAndVerifyStatus(ReligionType religionType, VerifyStatus verifyStatus, Pageable pageable);
}