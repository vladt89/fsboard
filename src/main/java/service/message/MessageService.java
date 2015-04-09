package main.java.service.message;

import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public interface MessageService {

    Collection<Message> fetchAllMessages();

    void createAndSaveMessage(String title, String content, String author, String url);
}
