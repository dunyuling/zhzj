package com.aifeng.dao;

import com.aifeng.constant.ReligionType;
import com.aifeng.model.Creed;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CreedRepository extends JpaRepository<Creed, Long> {
    List<Creed> findAll(Sort sort);

    Page<Creed> findAllByReligionType(Pageable pageable, ReligionType religionType);
}