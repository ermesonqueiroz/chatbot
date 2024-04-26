package com.ermesonqueiroz.chatbot;


import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("chatbot")
public record ChatbotConfigProperties(String openAiApiToken) {
}
