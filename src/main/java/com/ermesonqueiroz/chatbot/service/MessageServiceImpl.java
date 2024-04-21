package com.ermesonqueiroz.chatbot.service;

import com.ermesonqueiroz.chatbot.entity.Message;
import com.ermesonqueiroz.chatbot.repository.MessageRepository;
import com.ermesonqueiroz.chatbot.response.MessageResponse;
import com.ermesonqueiroz.chatbot.response.MessageResponseMapper;
import com.ermesonqueiroz.chatbot.entity.Customer;
import com.ermesonqueiroz.chatbot.repository.CustomerRepository;
import com.ermesonqueiroz.chatbot.request.CreateMessageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class MessageServiceImpl implements MessageService {
    private final CustomerRepository customerRepository;
    private final MessageRepository messageRepository;
    private final MessageResponseMapper messageResponseMapper;

    public MessageServiceImpl(CustomerRepository customerRepository, MessageRepository messageRepository, MessageResponseMapper messageResponseMapper) {
        this.customerRepository = customerRepository;
        this.messageRepository = messageRepository;
        this.messageResponseMapper = messageResponseMapper;
    }

    @Override
    public MessageResponse createMessage(String customerId, CreateMessageRequest createMessageRequest) {
        Optional<Customer> customer = customerRepository.findById(customerId);
        if (customer.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado");

        Message message = new Message(createMessageRequest.content(), createMessageRequest.role(), customer.get());
        messageRepository.save(message);

        return messageResponseMapper.fromModel(message);
    }

    @Override
    public List<MessageResponse> listMessagesFromCustomer(String customerId) {
        Optional<Customer> customer = customerRepository.findById(customerId);
        if (customer.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado");

        List<Message> messages = customer.get().getMessages();
        return messages.stream().map(messageResponseMapper::fromModel).toList();
    }
}
