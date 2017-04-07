package com.aifeng.service;

import com.aifeng.dao.BelieverChurchRepository;
import com.aifeng.model.Believer;
import com.aifeng.model.BelieverChurch;
import com.aifeng.model.Church;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by pro on 17-3-31.
 */
@Service
public class BelieverChurchService {

    @Autowired
    BelieverChurchRepository believerChurchRepository;

    @Transactional
    public void saveChurchRepository(Believer believer, Church church) {
        BelieverChurch believerChurch = new BelieverChurch();
        believerChurch.setBeliever(believer);
        believerChurch.setChurch(church);
        believerChurchRepository.save(believerChurch);
    }
}
