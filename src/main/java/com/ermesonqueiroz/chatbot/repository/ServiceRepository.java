package com.ermesonqueiroz.chatbot.repository;

import com.ermesonqueiroz.chatbot.entity.ServiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRepository extends JpaRepository<ServiceEntity, String> {
}
