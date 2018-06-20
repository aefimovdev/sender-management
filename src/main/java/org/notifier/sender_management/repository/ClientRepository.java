package org.notifier.sender_management.repository;

import org.notifier.sender_management.entity.Client;
import org.springframework.data.repository.CrudRepository;

public interface ClientRepository extends CrudRepository<Client, Long> {
}
