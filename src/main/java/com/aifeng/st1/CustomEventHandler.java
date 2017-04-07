package com.aifeng.st1;

import org.springframework.context.ApplicationListener;

/**
 * Created by pro on 17-4-6.
 */
public class CustomEventHandler implements ApplicationListener<CustomEvent> {

    public void onApplicationEvent(CustomEvent event) {
        System.out.println(event.toString());
    }
}
