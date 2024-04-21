package com.ermesonqueiroz.chatbot.controller;

import com.ermesonqueiroz.chatbot.response.CustomerResponse;
import com.ermesonqueiroz.chatbot.service.CustomerService;
import com.ermesonqueiroz.chatbot.request.CreateCustomerRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
@RequestMapping("/api/customers")
class CustomerController {
    private final CustomerService customerService;

    CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping()
    public CustomerResponse create(@RequestBody CreateCustomerRequest storeCustomerRequest) {
        try {
            return customerService.createCustomer(storeCustomerRequest);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/{id}")
    public CustomerResponse findById(@PathVariable("id") String customerId) {
        Optional<CustomerResponse> customer = this.customerService.findById(customerId);
        return customer.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Nenhum cliente encontrado"));
    }
}
