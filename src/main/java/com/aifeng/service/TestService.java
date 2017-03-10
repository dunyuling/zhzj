package com.aifeng.service;

import com.aifeng.dao.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by pro on 17-3-7.
 */
@Service
public class TestService {

    @Autowired
    TestRepository testRepository;

    @Transactional
    public void testSaveUser() {

    }
}