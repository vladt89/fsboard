package main.java.service.message;

import java.util.Collection;

public interface MessageService {

    Collection<Message> fetchAllMessages();

    void createAndSaveMessage(String title, String content, String author, String url);
}
