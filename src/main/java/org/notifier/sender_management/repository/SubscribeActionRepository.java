package org.notifier.sender_management.repository;

import org.notifier.sender_management.entity.Sender;
import org.notifier.sender_management.entity.SenderChannel;
import org.notifier.sender_management.entity.SubscribeAction;
import org.springframework.data.repository.CrudRepository;

public interface SubscribeActionRepository extends CrudRepository<SubscribeAction, Long> {

    SubscribeAction findBySenderAndSenderChannel(Sender sender, SenderChannel senderChannel);

    SubscribeAction findBySubscriberAndSenderAndSenderChannel(
            String subscriber, Sender sender, SenderChannel senderChannel);

    SubscribeAction findBySubscriber(String subscriber);

    void deleteBySubscriber(String subscriber);

    void deleteBySubscriberAndSenderChannel(String subscriber, SenderChannel senderChannel);

}
