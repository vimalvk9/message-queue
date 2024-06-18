package org.vimalvr9.entites;

import lombok.Getter;
import lombok.NonNull;

import java.util.UUID;

@Getter
public class Message {

    private String id;
    private String text;

    public Message(@NonNull final String text) {
        id = UUID.randomUUID().toString();
        this.text = text;
    }

}
