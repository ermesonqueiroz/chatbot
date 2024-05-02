package com.ermesonqueiroz.chatbot.service;

import com.ermesonqueiroz.chatbot.entity.Appointment;
import com.ermesonqueiroz.chatbot.entity.Customer;
import com.ermesonqueiroz.chatbot.entity.ServiceEntity;
import com.ermesonqueiroz.chatbot.repository.AppointmentRepository;
import com.ermesonqueiroz.chatbot.repository.CustomerRepository;
import com.ermesonqueiroz.chatbot.repository.ServiceRepository;
import dev.langchain4j.agent.tool.Tool;

import java.util.Optional;

public class AiAssistantTools {
    private final CustomerRepository customerRepository;
    private final AppointmentRepository appointmentRepository;
    private final ServiceRepository serviceRepository;

    public AiAssistantTools(CustomerRepository customerRepository, AppointmentRepository appointmentRepository, ServiceRepository serviceRepository) {
        this.customerRepository = customerRepository;
        this.appointmentRepository = appointmentRepository;
        this.serviceRepository = serviceRepository;
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

    @Tool("Salvar o nome do serviço que o cliente deseja agendar quando ele informar o nome do serviço para fornecer mais informações para o agendamento")
    void saveServiceName(String customerId, String serviceId) {
        Optional<Customer> customer = customerRepository.findById(customerId);
        if (customer.isEmpty()) return;

        Optional<Appointment> appointment = customer.get().getAppointments().stream().filter(item -> !item.getConfirmed()).findFirst();
        if (appointment.isEmpty()) return;

        System.out.printf(appointment.get().getId());

        Optional<ServiceEntity> service = serviceRepository.findById(serviceId);
        if (service.isEmpty()) return;

        appointment.get().setService(service.get());
        appointmentRepository.save(appointment.get());
    }
}
