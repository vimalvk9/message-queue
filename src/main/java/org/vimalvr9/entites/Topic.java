package org.vimalvr9.entites;


import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@ToString
public class Topic {

    private String id;
    private String name;
    private List<Message> messages;
    private List<SubscriberConsumption> subscribers;

    public Topic(@NonNull final String name) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.messages = new ArrayList<>();
        this.subscribers = new ArrayList<>();
    }

    public void addSubscriber(@NonNull final SubscriberConsumption subscriber) {
        subscribers.add(subscriber);
    }

    public synchronized void addMessage(@NonNull final Message message) {
        messages.add(message);
    }
}
