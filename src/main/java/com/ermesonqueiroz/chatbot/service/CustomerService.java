package com.ermesonqueiroz.chatbot.service;

import com.ermesonqueiroz.chatbot.request.CreateCustomerRequest;
import com.ermesonqueiroz.chatbot.response.CustomerResponse;

import java.util.Optional;

public interface CustomerService {
    CustomerResponse createCustomer(CreateCustomerRequest storeCustomerRequest) throws Exception;
    Optional<CustomerResponse> findById(String customerId);
//    void updateCustomerName(String customerId, String customerName);
}
