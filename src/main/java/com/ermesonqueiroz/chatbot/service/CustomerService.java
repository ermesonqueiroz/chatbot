package com.ermesonqueiroz.chatbot.service;

import com.ermesonqueiroz.chatbot.request.CreateCustomerRequest;
import com.ermesonqueiroz.chatbot.response.CustomerResponse;

import java.util.Optional;

public interface CustomerService {
    public abstract CustomerResponse createCustomer(CreateCustomerRequest storeCustomerRequest) throws Exception;
    public abstract Optional<CustomerResponse> findById(String customerId);
}
