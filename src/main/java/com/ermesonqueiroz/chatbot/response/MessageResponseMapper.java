package com.ermesonqueiroz.chatbot.response;

import com.ermesonqueiroz.chatbot.entity.Message;
import org.springframework.stereotype.Service;

@Service
public class MessageResponseMapper {
    public MessageResponse fromModel(Message message) {
        return new MessageResponse(message.getId(), message.getContent(), message.getRole());
    }
}
