package com.ermesonqueiroz.chatbot.service;

import com.ermesonqueiroz.chatbot.entity.Appointment;
import com.ermesonqueiroz.chatbot.entity.Customer;
import com.ermesonqueiroz.chatbot.repository.AppointmentRepository;
import com.ermesonqueiroz.chatbot.repository.CustomerRepository;
import dev.langchain4j.agent.tool.Tool;

import java.util.Optional;

public class AiAssistantTools {
    private final CustomerRepository customerRepository;
    private final AppointmentRepository appointmentRepository;

    public AiAssistantTools(CustomerRepository customerRepository, AppointmentRepository appointmentRepository) {
        this.customerRepository = customerRepository;
        this.appointmentRepository = appointmentRepository;
    }

    @Tool("Salvar o nome do cliente quando ele informar para fornecer mais informações para o agendamento")
    void saveCustomerName(String customerName, String customerId) {
        Optional<Customer> customer = customerRepository.findById(customerId);
        if (customer.isEmpty()) return;

        customer.get().setName(customerName);
        customerRepository.save(customer.get());
    }

    @Tool("Criar um registro na tabela de agendamentos quando o cliente demonstrar interesse de agendar um horário")
    void createAppointment(String customerId) {
        Optional<Customer> customer = customerRepository.findById(customerId);
        if (customer.isEmpty()) return;

        appointmentRepository.save(new Appointment(customer.get()));
    }
}
