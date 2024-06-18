package org.vimalvr9.handlers;

import lombok.NonNull;
import org.vimalvr9.entites.SubscriberConsumption;
import org.vimalvr9.entites.SubscriberWorker;
import org.vimalvr9.entites.Topic;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class TopicHandler {

    private final Topic topic;
    private final Map<String, SubscriberWorker> subscriberWorkers;

    public TopicHandler(@NonNull final Topic topic) {
        this.topic = topic;
        subscriberWorkers = new HashMap<>();
    }

    public void publish() {
        topic.getSubscribers().forEach(this::startWorker);
    }

    public void startWorker(SubscriberConsumption subscriberConsumption) {
        final String subscriberId = subscriberConsumption.getSubscriber().getId();
        SubscriberWorker worker = null;

        if (subscriberWorkers.containsKey(subscriberId)) {
            worker = subscriberWorkers.get(subscriberId);
        } else {
            worker = new SubscriberWorker(topic, subscriberConsumption);
            new Thread(worker).start();
        }

        worker.wakeUpIfNeeded();
    }
}
