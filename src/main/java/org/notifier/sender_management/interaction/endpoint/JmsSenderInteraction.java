package org.notifier.sender_management.interaction.endpoint;

import org.notifier.sender_management.dto.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service("jmsSenderInteraction")
public class JmsSenderInteraction implements ISenderInteraction {

    private Logger logger = LoggerFactory.getLogger(JmsSenderInteraction.class);

    @Override
    public RegistrationRsDto register(RegistrationRqDto message) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Boolean changeState(EnableMessagingRqDto message) {
        throw new UnsupportedOperationException();
    }

    @Override
    public RequestStateRsDto requestState(RequestStateRqDto message) {
        throw new UnsupportedOperationException();
    }
}
