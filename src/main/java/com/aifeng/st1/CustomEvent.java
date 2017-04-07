package com.aifeng.st1;

import org.springframework.context.ApplicationEvent;

/**
 * Created by pro on 17-4-6.
 */
public class CustomEvent extends ApplicationEvent {

    public CustomEvent(Object source) {
        super(source);
    }

    public String toString(){
        return "My Custom Event";
    }
}
