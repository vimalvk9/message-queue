package org.vimalvr9;

import lombok.NonNull;
import org.vimalvr9.abstractions.Subscriber;
import org.vimalvr9.entites.Message;
import org.vimalvr9.entites.SubscriberConsumption;
import org.vimalvr9.entites.Topic;
import org.vimalvr9.handlers.TopicHandler;

import java.util.HashMap;
import java.util.Map;

public class MessageQueue {
    private Map<String, TopicHandler> topicHandlers;

    public MessageQueue() {
        topicHandlers = new HashMap<>();
    }

    public Topic createTopic(@NonNull final String name) {
        final Topic topic = new Topic(name);
        TopicHandler topicHandler = new TopicHandler(topic);
        topicHandlers.put(topic.getId(), topicHandler);
        System.out.println("Created topic : " + topic);
        return topic;
    }

    public void subscribe(@NonNull Topic topic,
                          @NonNull Subscriber subscriber) {
        topic.addSubscriber(new SubscriberConsumption(subscriber));
        System.out.println("Subscriber " + subscriber.getName() + " subscribed to topic " + topic);
    }

    public void publish(@NonNull Topic topic,
                        @NonNull Message message) {
        topic.addMessage(message);
        System.out.println(message.getText() + " published to topic: " + topic.getName());
        new Thread(() -> topicHandlers.get(topic.getId()).publish()).start();
    }

    public void resetOffset(@NonNull Topic topic,
                            @NonNull Subscriber subscriber,
                            @NonNull Integer newOffset) {

        for (SubscriberConsumption currentSubscriber : topic.getSubscribers()) {
            if (currentSubscriber.getSubscriber().equals(subscriber)) {
                currentSubscriber.getOffset().set(newOffset);
                System.out.println("Subscriber " + currentSubscriber.getSubscriber().getName() + " has reset offset to " + newOffset);
                new Thread(() -> topicHandlers.get(topic.getId()).startWorker(currentSubscriber)).start();
                break;
            }
        }

    }
}
