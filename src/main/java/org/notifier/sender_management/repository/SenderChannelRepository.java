package org.notifier.sender_management.repository;

import org.notifier.sender_management.entity.SenderChannel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SenderChannelRepository extends CrudRepository<SenderChannel, Long> {

    SenderChannel findByGuid(String guid);

}
