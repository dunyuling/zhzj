package com.aifeng.dao;

import com.aifeng.constant.ReligionType;
import com.aifeng.model.Product;
import org.jboss.logging.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Page<Product> findAll(Pageable pageable);
    Page<Product> findAllByReligionType(ReligionType religionType,Pageable pageable);

    @Query(nativeQuery = true, value = "select * from product order by createtime desc limit ?1 offset ?2")
    public List<Product> findByPage(@Param int pageSize, @Param int offset);
}