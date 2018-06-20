package org.notifier.sender_management.dto;

import java.util.List;

public class EnableMessagingRqDto {

    private String senderId;

    private Boolean state;

    private String fromDate;

    private List<SenderChannelDto> senderChannels;

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public List<SenderChannelDto> getSenderChannels() {
        return senderChannels;
    }

    public void setSenderChannels(List<SenderChannelDto> senderChannels) {
        this.senderChannels = senderChannels;
    }

    @Override
    public String toString() {
        return "EnableMessagingRqDto{" +
                "senderId='" + senderId + '\'' +
                ", state=" + state +
                ", fromDate='" + fromDate + '\'' +
                ", senderChannels=" + senderChannels +
                '}';
    }
}
