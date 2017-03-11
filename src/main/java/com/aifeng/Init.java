package com.aifeng;

import com.aifeng.model.Manager;
import com.aifeng.service.ManagerService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * Created by pro on 17-3-11.
 */
public class Init {
    public static void main(String[] args) {
        System.out.println(System.getProperty("user.dir"));
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("classpath*:*.xml");
        ManagerService managerService = classPathXmlApplicationContext.getBean(ManagerService.class);
        managerService.saveManager("admin", "123456");
    }
}
