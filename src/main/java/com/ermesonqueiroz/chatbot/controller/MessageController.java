package com.ermesonqueiroz.chatbot.controller;

import com.ermesonqueiroz.chatbot.request.CreateMessageRequest;
import com.ermesonqueiroz.chatbot.response.MessageResponse;
import com.ermesonqueiroz.chatbot.service.MessageService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MessageController {
    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping("/customers/{customerId}/messages")
    public MessageResponse store(@PathVariable("customerId") String customerId, @RequestBody CreateMessageRequest createMessageRequest) {
        return this.messageService.createMessage(customerId, createMessageRequest);
    }

    @GetMapping("/customers/{customerId}/messages")
    public List<MessageResponse> listCustomerMessages(@PathVariable("customerId") String customerId) {
        return this.messageService.listMessagesFromCustomer(customerId);
    }
}
