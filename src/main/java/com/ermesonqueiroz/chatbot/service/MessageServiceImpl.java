package com.ermesonqueiroz.chatbot.service;

import com.ermesonqueiroz.chatbot.ChatbotConfigProperties;
import com.ermesonqueiroz.chatbot.entity.Message;
import com.ermesonqueiroz.chatbot.entity.MessageRole;
import com.ermesonqueiroz.chatbot.exceptions.CustomerNotFoundException;
import com.ermesonqueiroz.chatbot.repository.AppointmentRepository;
import com.ermesonqueiroz.chatbot.repository.MessageRepository;
import com.ermesonqueiroz.chatbot.request.ReceiveMessageRequest;
import com.ermesonqueiroz.chatbot.response.MessageResponse;
import com.ermesonqueiroz.chatbot.response.MessageResponseMapper;
import com.ermesonqueiroz.chatbot.entity.Customer;
import com.ermesonqueiroz.chatbot.repository.CustomerRepository;
import com.ermesonqueiroz.chatbot.request.CreateMessageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class MessageServiceImpl implements MessageService {
    private final CustomerRepository customerRepository;
    private final MessageRepository messageRepository;
    private final MessageResponseMapper messageResponseMapper;
    private final ChatbotConfigProperties chatbotConfigProperties;
    private final AppointmentRepository appointmentRepository;

    public MessageServiceImpl(CustomerRepository customerRepository, MessageRepository messageRepository, MessageResponseMapper messageResponseMapper, ChatbotConfigProperties chatbotConfigProperties, AppointmentRepository appointmentRepository) {
        this.customerRepository = customerRepository;
        this.messageRepository = messageRepository;
        this.messageResponseMapper = messageResponseMapper;
        this.chatbotConfigProperties = chatbotConfigProperties;
        this.appointmentRepository = appointmentRepository;
    }

    @Override
    public MessageResponse createMessage(String customerId, CreateMessageRequest createMessageRequest) {
        Optional<Customer> customer = customerRepository.findById(customerId);
        if (customer.isEmpty()) throw new CustomerNotFoundException();

        Message message = new Message(createMessageRequest.content(), createMessageRequest.role(), customer.get());
        messageRepository.save(message);

        return messageResponseMapper.fromModel(message);
    }

    @Override
    public List<MessageResponse> listMessagesFromCustomer(String customerId) {
        Optional<Customer> customer = customerRepository.findById(customerId);
        if (customer.isEmpty()) throw new CustomerNotFoundException();

        List<Message> messages = customer.get().getMessages();
        messages.sort(Comparator.comparing(Message::getCreatedAt));

        return messages.stream().map(messageResponseMapper::fromModel).toList();
    }

    @Override
    public MessageResponse handleChatUserMessage(ReceiveMessageRequest receiveMessageRequest) {
        Optional<Customer> customer = customerRepository.findByPhoneNumber(receiveMessageRequest.From());
        customer.orElseGet(() -> customerRepository.save(new Customer(receiveMessageRequest.From(), null)));

        if (customer.isEmpty())
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro interno do servidor");

        this.createMessage(customer.get().getId(), new CreateMessageRequest(receiveMessageRequest.Body(), MessageRole.USER));
        AiAssistant aiAssistant = new AiAssistant(chatbotConfigProperties, customer.get().getId(), customerRepository, appointmentRepository);

        return this.createMessage(customer.get().getId(), new CreateMessageRequest(aiAssistant.chat(receiveMessageRequest.Body()), MessageRole.MODEL));
    }
}
