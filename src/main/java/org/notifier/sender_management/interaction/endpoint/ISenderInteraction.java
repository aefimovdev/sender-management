package org.notifier.sender_management.interaction.endpoint;

import org.notifier.sender_management.dto.*;

public interface ISenderInteraction {

    RegistrationRsDto register(RegistrationRqDto message);

    Boolean changeState(EnableMessagingRqDto message);

    RequestStateRsDto requestState(RequestStateRqDto message);

}
