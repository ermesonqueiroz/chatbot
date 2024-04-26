package com.ermesonqueiroz.chatbot.controller;

import com.ermesonqueiroz.chatbot.request.ReceiveMessageRequest;
import com.ermesonqueiroz.chatbot.response.MessageResponse;
import com.ermesonqueiroz.chatbot.service.MessageService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/webhooks")
public class WebhookController {
    private final MessageService messageService;

    public WebhookController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping("/messages")
    public MessageResponse receiveMessage(@RequestBody ReceiveMessageRequest receiveMessageRequest) {
        return messageService.handleChatUserMessage(receiveMessageRequest);
    }
}
