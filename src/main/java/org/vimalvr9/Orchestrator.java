package org.vimalvr9;

import lombok.SneakyThrows;
import org.vimalvr9.entites.Message;
import org.vimalvr9.entites.Topic;
import org.vimalvr9.subscribers.SleepingSubscriber;

public class Orchestrator {

    @SneakyThrows
    public static void main(String[] args) {

        System.out.println("Hello world!, welcome to our own multithreaded message queue");

        final MessageQueue queue = new MessageQueue();
        final Topic t1 = queue.createTopic("1st topic");
        final SleepingSubscriber sub1 = new SleepingSubscriber("sub1", 2000);

        queue.subscribe(t1, sub1);
        queue.publish(t1, new Message("message 0"));
        queue.publish(t1, new Message("message 1"));
        queue.publish(t1, new Message("message 2"));

        Thread.sleep(5000);

        queue.publish(t1, new Message("message 3"));
        queue.publish(t1, new Message("message 4"));
        queue.publish(t1, new Message("message 5"));

        queue.resetOffset(t1, sub1, 4);
    }
}