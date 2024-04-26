package com.ermesonqueiroz.chatbot.service;

import com.ermesonqueiroz.chatbot.entity.Customer;
import com.ermesonqueiroz.chatbot.repository.CustomerRepository;
import dev.langchain4j.agent.tool.Tool;

import java.util.Optional;

public class AiAssistantTools {
    private final CustomerRepository customerRepository;

    public AiAssistantTools(CustomerRepository CustomerRepository) {
        this.customerRepository = CustomerRepository;
    }

    @Tool("Salvar o nome do cliente quando ele informar para fornecer mais informações para o agendamento.")
    void saveCustomerName(String customerName, String customerId) {
        Optional<Customer> customer = customerRepository.findById(customerId);
        if (customer.isEmpty()) return;

        customer.get().setName(customerName);
        customerRepository.save(customer.get());
    }
}
