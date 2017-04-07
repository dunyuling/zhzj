package com.aifeng.st1;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;

/**
 * Created by pro on 17-4-6.
 */
public class CustomEventPublisher implements ApplicationEventPublisherAware {

    private ApplicationEventPublisher publisher;

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

    public void publish() {
        CustomEvent customEvent = new CustomEvent(this);
        publisher.publishEvent(customEvent);

    }
}
