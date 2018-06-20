package org.notifier.sender_management.service;

import org.notifier.sender_management.dto.subscribe.SubscribeActionDto;

public interface ISubscribeActionService {

    void subscribe(SubscribeActionDto subscribeActionDto);

    void unsubscribeAll(SubscribeActionDto subscribeActionDto);

    void unsubscribe(SubscribeActionDto subscribeActionDto);

}
