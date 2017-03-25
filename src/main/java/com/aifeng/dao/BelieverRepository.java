package com.aifeng.dao;

import com.aifeng.model.Believer;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BelieverRepository extends JpaRepository<Believer, Long> {
    List<Believer> findAll(Sort sort);
}