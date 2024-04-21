package com.ermesonqueiroz.chatbot.response;

import com.ermesonqueiroz.chatbot.entity.MessageRole;

public record MessageResponse(String id, String content, MessageRole role) {
}
