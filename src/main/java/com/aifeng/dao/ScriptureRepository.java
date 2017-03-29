package com.aifeng.dao;

import com.aifeng.constant.ReligionType;
import com.aifeng.model.Creed;
import com.aifeng.model.Scripture;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScriptureRepository extends JpaRepository<Scripture, Long> {
    List<Scripture> findAll(Sort sort);

    Page<Scripture> findAllByReligionType(Pageable pageable, ReligionType religionType);
}