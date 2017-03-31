package com.aifeng;

import com.aifeng.constant.ReligionType;
import com.aifeng.service.BelieverService;
import com.aifeng.service.ChurchService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by pro on 17-3-11.
 */
public class Init {
    public static void main(String[] args) {
        System.out.println(System.getProperty("user.dir"));
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("classpath*:*.xml");
//        BelieverService believerService = classPathXmlApplicationContext.getBean(BelieverService.class);
//        believerService.saveUser("admin", "123456");

        ChurchService churchService = classPathXmlApplicationContext.getBean(ChurchService.class);
        churchService.saveChurch("教会一","地址一","0393-1234567", ReligionType.BUDDHISM);
        churchService.saveChurch("教会二","地址二","0393-1234567", ReligionType.BUDDHISM);
        churchService.saveChurch("教会三","地址三","0393-1234567", ReligionType.BUDDHISM);
        churchService.saveChurch("教会四","地址四","0393-1234567", ReligionType.BUDDHISM);
    }
}
