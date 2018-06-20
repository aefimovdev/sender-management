package org.notifier.sender_management.interaction;

import org.notifier.sender_management.configuration.property.InteractionProperties;
import org.notifier.sender_management.dto.*;
import org.notifier.sender_management.interaction.endpoint.ISenderInteraction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Map;


@Service
public class SenderInteractionProducer implements ISenderInteractionProducer {

    private final InteractionProperties interactionProperties;
    private final Map<String, ISenderInteraction> senderInteractionStrategies;

    private ISenderInteraction senderInteractionStrategy;

    @Autowired
    public SenderInteractionProducer(InteractionProperties interactionProperties,
                                     Map<String, ISenderInteraction> senderInteractionStrategies) {

        this.interactionProperties = interactionProperties;
        this.senderInteractionStrategies = senderInteractionStrategies;
    }

    @Override
    public RegistrationRsDto register(RegistrationRqDto message) {
        return this.senderInteractionStrategy.register(message);
    }

    @Override
    public Boolean changeState(EnableMessagingRqDto message) {
        return this.senderInteractionStrategy.changeState(message);
    }

    @Override
    public RequestStateRsDto requestState(RequestStateRqDto message) {
        return this.senderInteractionStrategy.requestState(message);
    }

    @PostConstruct
    public void init() {
        String endpointType = this.interactionProperties.getSenderEndpointType();
        ISenderInteraction senderInteractionStrategy = this.senderInteractionStrategies.get(endpointType);
        if (senderInteractionStrategy == null)
            throw new IllegalStateException("");
        this.senderInteractionStrategy = senderInteractionStrategy;
    }

}
