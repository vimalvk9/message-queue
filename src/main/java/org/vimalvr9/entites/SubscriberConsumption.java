package org.vimalvr9.entites;

import lombok.Getter;
import lombok.ToString;
import org.vimalvr9.abstractions.Subscriber;

import java.util.concurrent.atomic.AtomicInteger;

@Getter
@ToString
public class SubscriberConsumption {
    private AtomicInteger offset;
    private Subscriber subscriber;

    public SubscriberConsumption(Subscriber subscriber) {
        this.subscriber = subscriber;
        this.offset = new AtomicInteger(0);
    }
}
