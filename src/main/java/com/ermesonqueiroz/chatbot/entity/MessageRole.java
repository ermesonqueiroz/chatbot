package com.ermesonqueiroz.chatbot.entity;

import com.fasterxml.jackson.annotation.JsonValue;

public enum MessageRole {
    USER("user"),
    MODEL("model");

    private final String role;

    private MessageRole(String role) {
        this.role = role;
    }

    @JsonValue
    public String getValue() {
        return this.role;
    }

    public static MessageRole valueOfIgnoreCase(String role) {
        return valueOf(role.toUpperCase());
    }
}
