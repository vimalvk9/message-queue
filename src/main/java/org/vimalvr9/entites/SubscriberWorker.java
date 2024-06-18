package org.vimalvr9.entites;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.SneakyThrows;

@Getter
@AllArgsConstructor
public class SubscriberWorker implements Runnable  {

    private final Topic topic;
    private final SubscriberConsumption subscriberConsumption;

    @SneakyThrows
    @Override
    public void run() {

        synchronized (subscriberConsumption) {

            do {
                int currentOffset = subscriberConsumption.getOffset().get();

                while (currentOffset >= topic.getMessages().size()) {
                    subscriberConsumption.wait();
                }

                Message messageToConsume = topic.getMessages().get(currentOffset);
                subscriberConsumption.getSubscriber().consumer(messageToConsume);

                // We cannot just increment here since subscriber offset can be reset while it is consuming. So, after
                // consuming we need to increase only if it was previous one.
                subscriberConsumption.getOffset().compareAndSet(currentOffset, currentOffset+1);

            } while (true);
        }
    }


    public void wakeUpIfNeeded() {
        synchronized (subscriberConsumption) {
            subscriberConsumption.notify();
        }
    }
}
