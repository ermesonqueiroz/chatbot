package com.ermesonqueiroz.chatbot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ermesonqueiroz.chatbot.entity.Customer;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, String> {
    Optional<Customer> findByPhoneNumber(String phoneNumber);
}
