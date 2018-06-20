package org.notifier.sender_management.dto;

public class RegistrationRqDto {

    private String senderId;

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    @Override
    public String toString() {
        return "RegistrationRqDto{" +
                "senderId='" + senderId + '\'' +
                '}';
    }
}
