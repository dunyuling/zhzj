package com.aifeng.dao;

import com.aifeng.model.Believer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BelieverRepository extends JpaRepository<Believer, Long> {
    Believer findByNameAndPassword(String username, String password);
}
