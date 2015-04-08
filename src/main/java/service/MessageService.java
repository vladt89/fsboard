package main.java.service;

import java.util.Collection;
import java.util.List;

public interface MessageService {

    void createMessage(String message);

    Collection<String> fetchAllMessages();
}
