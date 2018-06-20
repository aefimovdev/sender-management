package org.notifier.sender_management.interaction;

import org.notifier.sender_management.dto.*;

public interface ISenderInteractionProducer {

    RegistrationRsDto register(RegistrationRqDto message);

    Boolean changeState(EnableMessagingRqDto message);

    RequestStateRsDto requestState(RequestStateRqDto message);

}
