package com.ermesonqueiroz.chatbot.service;

import com.ermesonqueiroz.chatbot.entity.Customer;
import com.ermesonqueiroz.chatbot.repository.CustomerRepository;
import com.ermesonqueiroz.chatbot.request.CreateCustomerRequest;
import com.ermesonqueiroz.chatbot.response.CustomerResponse;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public CustomerResponse createCustomer(CreateCustomerRequest storeCustomerRequest) throws Exception {
        Optional<Customer> customerAlreadyExists = this.customerRepository.findByPhoneNumber(storeCustomerRequest.phone_number());

        if (customerAlreadyExists.isPresent()) {
            throw new Exception(String.format("Outro cliente já possui esse número de telefone (%s)", storeCustomerRequest.phone_number()));
        }

        Customer customer = new Customer(storeCustomerRequest.phone_number(), storeCustomerRequest.name());
        this.customerRepository.save(customer);

        return new CustomerResponse(customer.getId(), customer.getName(), customer.getPhoneNumber());
    }

    @Override
    public Optional<CustomerResponse> findById(String customerId) {
        Optional<Customer> customer = this.customerRepository.findById(customerId);

        return customer.map(value -> new CustomerResponse(value.getId(), value.getName(), value.getPhoneNumber()));
    }
}
