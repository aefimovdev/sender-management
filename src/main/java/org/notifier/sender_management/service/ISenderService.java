package org.notifier.sender_management.service;

import org.notifier.sender_management.entity.Sender;
import org.notifier.sender_management.entity.SenderChannel;

import java.util.List;
import java.util.Optional;

public interface ISenderService {

    Boolean isExist(Sender sender);

    void register(Sender sender);

    void validate(Sender sender);

    void validate(Long senderId);

    Optional<Sender> findById(long id);

    Iterable<Sender> findAll();

    void createChannel(SenderChannel senderChannel);

}
