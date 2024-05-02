package com.ermesonqueiroz.chatbot.service;

import com.ermesonqueiroz.chatbot.ChatbotConfigProperties;
import com.ermesonqueiroz.chatbot.repository.AppointmentRepository;
import com.ermesonqueiroz.chatbot.repository.CustomerRepository;
import com.ermesonqueiroz.chatbot.repository.ServiceRepository;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.service.AiServices;

import java.util.List;

public class AiAssistant {
    private final Assistant assistant;

    interface Assistant {
        String chat(String message);
    }

    public AiAssistant(ChatbotConfigProperties chatbotConfigProperties, String customerId, CustomerRepository customerRepository, AppointmentRepository appointmentRepository, ServiceRepository serviceRepository) {
        OpenAiChatModel model = OpenAiChatModel.builder()
                .apiKey(chatbotConfigProperties.openAiApiToken())
                .modelName("gpt-3.5-turbo")
                .build();

        List<String> services = serviceRepository.findAll().stream().map(service -> String.format("ID %s, Nome: %s", service.getId(), service.getName())).toList();

        this.assistant = AiServices.builder(Assistant.class)
                .chatLanguageModel(model)
                .tools(new AiAssistantTools(customerRepository, appointmentRepository, serviceRepository))
                .systemMessageProvider(chatMemoryId -> String.format("Seu papel é auxiliar os clientes agendar seus atendimentos, para isso você deve pedir o nome do cliente e o serviço que ele quer, exatamente nessa ordem. Lembre-se de sempre responder em Português Brasil. O ID do cliente é '%s'. Os serviços disponíveis são:\n%s", customerId, String.join("\n", services)))
                .build();
    }

    public String chat(String message) {
        return assistant.chat(message);
    }
}
