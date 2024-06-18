package org.vimalvr9.subscribers;

import lombok.*;
import org.vimalvr9.abstractions.Subscriber;
import org.vimalvr9.entites.Message;

import java.util.UUID;

@Getter
@Setter
@ToString
public class SleepingSubscriber extends Subscriber {

    private final int sleepTimeInMillis;

    public SleepingSubscriber( String name, int sleepTimeInMillis) {
        super(UUID.randomUUID().toString(), name);
        this.sleepTimeInMillis = sleepTimeInMillis;
    }

    @Override
    @SneakyThrows
    public void consumer(Message message) {

        System.out.println("Subscriber: " + getName() + " started consuming messageId : "
                + message.getId() + " with text"
                + message.getText());

        Thread.sleep(sleepTimeInMillis);

        System.out.println("Subscriber: " + getName() + " done consuming messageId : "
                + message.getId() + " with text"
                + message.getText());
    }
}
