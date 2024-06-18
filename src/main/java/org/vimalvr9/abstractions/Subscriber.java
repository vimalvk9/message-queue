package org.vimalvr9.abstractions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.vimalvr9.entites.Message;

@AllArgsConstructor
@Getter
@ToString
public abstract class Subscriber{

    private String id;
    private String name;
    public abstract void consumer(Message message);
}
