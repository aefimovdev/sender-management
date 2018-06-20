package org.notifier.sender_management.service.impl;

import org.notifier.sender_management.dto.EnableMessagingRqDto;
import org.notifier.sender_management.dto.RegistrationRqDto;
import org.notifier.sender_management.dto.RegistrationRsDto;
import org.notifier.sender_management.entity.Sender;
import org.notifier.sender_management.entity.SenderChannel;
import org.notifier.sender_management.interaction.ISenderInteractionProducer;
import org.notifier.sender_management.repository.SenderChannelRepository;
import org.notifier.sender_management.repository.SenderRepository;
import org.notifier.sender_management.service.ISenderService;
import org.notifier.sender_management.service.type.SenderStateType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
public class SenderServiceImpl implements ISenderService {
    private Logger logger = LoggerFactory.getLogger(SenderServiceImpl.class);

    private final SenderRepository senderRepository;
    private final SenderChannelRepository channelRepository;

    private final ISenderInteractionProducer senderInteractionProducer;

    public SenderServiceImpl(SenderRepository senderRepository,
                             SenderChannelRepository channelRepository,
                             ISenderInteractionProducer senderInteractionProducer) {
        this.senderRepository = senderRepository;
        this.channelRepository = channelRepository;
        this.senderInteractionProducer = senderInteractionProducer;
    }

    @Override
    public Optional<Sender> findById(long id) {
        return this.senderRepository.findById(id);
    }

    @Override
    public Iterable<Sender> findAll() {
        return this.senderRepository.findAll();
    }

    @Override
    public Boolean isExist(Sender sender) {
        Sender byEmail = this.senderRepository.findByEmail(sender.getEmail());
        return byEmail != null;
    }

    @Override
    public void register(Sender sender) {
        CompletableFuture.supplyAsync(() -> {
            sender.setState(SenderStateType.CREATED);
            return this.senderRepository.save(sender);
        }).thenApplyAsync(result -> {
            RegistrationRqDto message = new RegistrationRqDto();
            message.setSenderId(String.valueOf(result.getId()));
            RegistrationRsDto response = senderInteractionProducer.register(message);

            return result;
        }).handle((result, ex) -> {
            if (ex != null) {
                logger.error("An error occurred when try to register messaging for sender {}.", sender, ex);
            }
            return result;
        });
    }

    @Override
    public void validate(Long senderId) {
        Optional<Sender> byId = this.senderRepository.findById(senderId);
        if (byId.isPresent()) {
            Sender sender = byId.get();
            CompletableFuture<Sender> future = CompletableFuture.supplyAsync(() -> {
                sender.setState(SenderStateType.VALIDATED);
                return this.senderRepository.save(sender);
            }).thenApply(result -> {
                EnableMessagingRqDto message = new EnableMessagingRqDto();
                message.setSenderId(String.valueOf(result.getId()));
                message.setFromDate("2018-05-25 00:00:00.000000");
                senderInteractionProducer.changeState(message);
                result.setState(SenderStateType.READY);
                return result;
            }).thenApply(this.senderRepository::save);
        }
    }

    @Override
    public void validate(Sender sender) {

    }

    @Override
    public void createChannel(SenderChannel senderChannel) {
        long senderId = senderChannel.getSender().getId();
        if (this.senderRepository.existsById(senderId)) {
            senderChannel.setEnabled(Boolean.FALSE);
            this.channelRepository.save(senderChannel);
        }
    }
}
