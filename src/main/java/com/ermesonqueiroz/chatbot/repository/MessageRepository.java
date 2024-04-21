package com.ermesonqueiroz.chatbot.repository;

import com.ermesonqueiroz.chatbot.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, String> {
}
