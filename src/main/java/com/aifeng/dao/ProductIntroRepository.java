package com.aifeng.dao;

import com.aifeng.model.ProductIntro;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductIntroRepository extends JpaRepository<ProductIntro, Long> {
    List<ProductIntro> findAll(Sort sort);
}