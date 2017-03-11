package com.aifeng.service;

import com.aifeng.dao.ManagerRepository;
import com.aifeng.model.Manager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by pro on 17-3-11.
 */
@Service
public class ManagerService {

    @Autowired
    ManagerRepository managerRepository;

    @Transactional
    public Manager findManager(String username ,String password) {
        Manager manager = managerRepository.findByNameAndPassword(username,password);
        return manager;
    }

    @Transactional
    public void saveManager(String username,String password) {
        Manager manager = new Manager();
        manager.setName(username);
        manager.setPassword(password);
        managerRepository.save(manager);
    }

}
