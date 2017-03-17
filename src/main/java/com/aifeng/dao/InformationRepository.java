package com.aifeng.dao;

import com.aifeng.model.Information;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by pro on 17-3-17.
 */
@Repository
public interface InformationRepository extends JpaRepository<Information,Long> {

}