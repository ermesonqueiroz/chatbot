package com.ermesonqueiroz.chatbot.service;

import com.ermesonqueiroz.chatbot.ChatbotConfigProperties;
import com.ermesonqueiroz.chatbot.repository.AppointmentRepository;
import com.ermesonqueiroz.chatbot.repository.CustomerRepository;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.service.AiServices;

public class AiAssistant {
    private final Assistant assistant;

    interface Assistant {
        String chat(String message);
    }

    public AiAssistant(ChatbotConfigProperties chatbotConfigProperties, String customerId, CustomerRepository customerRepository, AppointmentRepository appointmentRepository) {
        OpenAiChatModel model = OpenAiChatModel.builder()
                .apiKey(chatbotConfigProperties.openAiApiToken())
                .modelName("gpt-3.5-turbo")
                .build();

        this.assistant = AiServices.builder(Assistant.class)
                .chatLanguageModel(model)
                .tools(new AiAssistantTools(customerRepository, appointmentRepository))
                .systemMessageProvider(chatMemoryId -> String.format("Lembre-se de sempre responder em Português Brasil. O ID do cliente é '%s'", customerId))
                .build();
    }

    public String chat(String message) {
        return assistant.chat(message);
    }
}
