package org.notifier.sender_management.dto;

import java.util.List;

public class EnableMessagingRsDto {

    private String senderId;

    private Boolean completed;

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }
}
