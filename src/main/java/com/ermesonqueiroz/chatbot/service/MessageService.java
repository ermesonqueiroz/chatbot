package com.ermesonqueiroz.chatbot.service;

import com.ermesonqueiroz.chatbot.response.MessageResponse;
import com.ermesonqueiroz.chatbot.request.CreateMessageRequest;

import java.util.List;

public interface MessageService {
    public abstract MessageResponse createMessage(String customerId, CreateMessageRequest message);
    public abstract List<MessageResponse> listMessagesFromCustomer(String customerId);
}
