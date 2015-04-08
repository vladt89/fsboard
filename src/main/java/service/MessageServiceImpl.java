package main.java.service;

import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.LinkedList;

@Component
public class MessageServiceImpl implements MessageService {
    private Collection<Message> messageLibrary = new LinkedList<>();

    @Override
    public void saveMessage(Message message) {
        messageLibrary.add(message);
    }

    @Override
    public Collection<Message> fetchAllMessages() {
        return messageLibrary;
    }
}
