package com.ermesonqueiroz.chatbot.entity;

import jakarta.persistence.*;

@Entity(name = "messages")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String content;
    private String role;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    public Message() {
    }

    public Message(String content, MessageRole role, Customer customer) {
        this.content = content;
        this.role = role.getValue();
        this.customer = customer;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public MessageRole getRole() {
        return MessageRole.valueOfIgnoreCase(this.role);
    }

    public void setRole(MessageRole role) {
        this.role = role.getValue();
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
