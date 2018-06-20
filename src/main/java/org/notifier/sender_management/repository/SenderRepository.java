package org.notifier.sender_management.repository;

import org.notifier.sender_management.entity.Sender;
import org.springframework.data.repository.CrudRepository;

public interface SenderRepository extends CrudRepository<Sender, Long> {

    Sender findByEmail(String email);

    Sender findByGuid(String guid);

}
