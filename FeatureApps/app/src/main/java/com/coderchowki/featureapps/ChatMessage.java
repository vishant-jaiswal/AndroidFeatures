package com.coderchowki.featureapps;

/**
 * Created by Vishant on 11-04-2016.
 */
public class ChatMessage {
    String message;
    String name;

    public ChatMessage() {
    }

    public ChatMessage(String name, String message) {
        this.message = message;
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public String getName() {
        return name;
    }
}
