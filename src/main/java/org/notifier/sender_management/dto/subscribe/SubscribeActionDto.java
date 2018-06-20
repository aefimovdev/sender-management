package org.notifier.sender_management.dto.subscribe;

import java.io.Serializable;

public class SubscribeActionDto implements Serializable {

    private String subscriber;

    private String sender;

    private String channel;

    public String getSubscriber() {
        return subscriber;
    }

    public void setSubscriber(String subscriber) {
        this.subscriber = subscriber;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }
}
