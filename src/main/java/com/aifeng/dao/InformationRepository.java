package com.aifeng.dao;

import com.aifeng.constant.InformationPublisher;
import com.aifeng.constant.VerifyStatus;
import com.aifeng.model.Information;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by pro on 17-3-17.
 */
@Repository
public interface InformationRepository extends JpaRepository<Information, Long> {
    List<Information> findAllByInformationPublisher(@Param("informationPublisher") InformationPublisher ip, Sort sort);

    List<Information> findAllByInformationPublisherAndVerifyStatus(@Param("informationPublisher") InformationPublisher ip,
                                                                   @Param("verifyStatus") VerifyStatus verifyStatus, Sort sort);
}