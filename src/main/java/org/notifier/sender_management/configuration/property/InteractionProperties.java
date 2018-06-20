package org.notifier.sender_management.configuration.property;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app.configuration.interaction")
public class InteractionProperties {

    private String senderEndpointType;

    public String getSenderEndpointType() {
        return senderEndpointType;
    }

    public void setSenderEndpointType(String senderEndpointType) {
        this.senderEndpointType = senderEndpointType;
    }
}
