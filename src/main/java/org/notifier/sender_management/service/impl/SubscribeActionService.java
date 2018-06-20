package org.notifier.sender_management.service.impl;

import org.notifier.sender_management.dto.subscribe.SubscribeActionDto;
import org.notifier.sender_management.entity.Sender;
import org.notifier.sender_management.entity.SenderChannel;
import org.notifier.sender_management.entity.SubscribeAction;
import org.notifier.sender_management.repository.SenderChannelRepository;
import org.notifier.sender_management.repository.SenderRepository;
import org.notifier.sender_management.repository.SubscribeActionRepository;
import org.notifier.sender_management.service.ISubscribeActionService;
import org.notifier.sender_management.service.type.SenderStateType;
import org.springframework.stereotype.Service;

@Service
public class SubscribeActionService implements ISubscribeActionService {

    private final SenderRepository senderRepository;
    private final SenderChannelRepository senderChannelRepository;
    private final SubscribeActionRepository subscribeActionRepository;

    public SubscribeActionService(SenderRepository senderRepository,
                                  SenderChannelRepository senderChannelRepository,
                                  SubscribeActionRepository subscribeActionRepository) {
        this.senderRepository = senderRepository;
        this.senderChannelRepository = senderChannelRepository;
        this.subscribeActionRepository = subscribeActionRepository;
    }

    @Override
    public void subscribe(SubscribeActionDto dto) {
        String senderGuid = dto.getSender();
        Sender foundSender = this.senderRepository.findByGuid(senderGuid);
        if (foundSender == null)
            throw new IllegalStateException(String
                    .format("The sender is not found by guid '%s'!", senderGuid));

        SenderStateType state = foundSender.getState();
        if (SenderStateType.READY != state)
            throw new IllegalStateException(String
                    .format("The sender with guid '%s' is not ready for subscribing!", senderGuid));

        String channelGuid = dto.getChannel();
        SenderChannel foundChannel = this.senderChannelRepository.findByGuid(channelGuid);
        if (foundChannel == null)
            throw new IllegalStateException(String
                    .format("The sender-channel is not found by guid '%s'!", channelGuid));

        String subscriber = dto.getSubscriber();
        SubscribeAction sa = this.subscribeActionRepository
                .findBySubscriberAndSenderAndSenderChannel(subscriber, foundSender, foundChannel);

        if (sa != null)
            throw new IllegalStateException(String
                    .format("The subscriber '%s' has already subscribed!", subscriber));

        SubscribeAction subscribeAction = new SubscribeAction();
        subscribeAction.setSender(foundSender);
        subscribeAction.setSenderChannel(foundChannel);
        subscribeAction.setSubscriber(subscriber);
        this.subscribeActionRepository.save(subscribeAction);
    }

    @Override
    public void unsubscribeAll(SubscribeActionDto dto) {
        this.subscribeActionRepository.deleteBySubscriber(dto.getSubscriber());
    }

    @Override
    public void unsubscribe(SubscribeActionDto dto) {
        SenderChannel sc = this.senderChannelRepository.findByGuid(dto.getChannel());
        if (sc != null){
            this.subscribeActionRepository.deleteBySubscriberAndSenderChannel(dto.getSubscriber(), sc);
        }
    }
}
