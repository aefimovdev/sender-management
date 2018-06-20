package org.notifier.sender_management.interaction.endpoint;

import org.notifier.sender_management.dto.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service("mockSenderInteraction")
public class MockSenderInteraction implements ISenderInteraction {

    private Logger logger = LoggerFactory.getLogger(MockSenderInteraction.class);

    @Override
    public RegistrationRsDto register(RegistrationRqDto message) {
        logger.info("The interaction for register sender was executed. Sender is {}.", message);
        return null;
    }

    @Override
    public Boolean changeState(EnableMessagingRqDto message) {
        logger.info("The interaction for change sender state was executed. Sender is {}.", message);
        return false;
    }

    @Override
    public RequestStateRsDto requestState(RequestStateRqDto message) {
        logger.info("The interaction for request sender state was executed. Sender is {}.", message);
        return null;
    }
}
