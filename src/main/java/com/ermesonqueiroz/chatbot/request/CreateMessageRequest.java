package com.ermesonqueiroz.chatbot.request;

import com.ermesonqueiroz.chatbot.entity.MessageRole;

public record CreateMessageRequest(String content, MessageRole role) {
}
