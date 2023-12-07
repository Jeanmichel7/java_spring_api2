package fr.lde.apitest.domainLayer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.lde.apitest.domainLayer.model.Message;

@Repository
public interface MessageRepository
    extends JpaRepository<Message, Long> {
}
