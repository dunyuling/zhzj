package com.aifeng.dao;

import com.aifeng.model.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManagerRepository extends JpaRepository<Manager,Long> {
    Manager findByNameAndPassword(String username,String password);
}