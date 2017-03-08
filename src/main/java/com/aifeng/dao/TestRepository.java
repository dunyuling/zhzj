package com.aifeng.dao;

import com.aifeng.model.User1;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestRepository extends JpaRepository<User1,Long> {
}
