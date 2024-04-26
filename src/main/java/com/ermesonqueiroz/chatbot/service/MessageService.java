package com.ermesonqueiroz.chatbot.service;

import com.ermesonqueiroz.chatbot.request.ReceiveMessageRequest;
import com.ermesonqueiroz.chatbot.response.MessageResponse;
import com.ermesonqueiroz.chatbot.request.CreateMessageRequest;

import java.util.List;

public interface MessageService {
    MessageResponse createMessage(String customerId, CreateMessageRequest message);
    List<MessageResponse> listMessagesFromCustomer(String customerId);
    MessageResponse handleChatUserMessage(ReceiveMessageRequest receiveMessageRequest);
}
