package main.java.service;

import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public interface MessageService {

    void saveMessage(Message message);

    Collection<Message> fetchAllMessages();
}
