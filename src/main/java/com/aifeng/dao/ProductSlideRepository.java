package com.aifeng.dao;

import com.aifeng.model.Product;
import com.aifeng.model.ProductSlide;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductSlideRepository extends JpaRepository<ProductSlide, Long> {
    List<ProductSlide> findAll(Sort sort);

    List<ProductSlide> findAllByProduct(Product product, Sort sort);
}