package main.java.service.message;

import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public interface MessageService {

    void saveMessage(Message message);

    Collection<Message> fetchAllMessages();
}
