package com.ermesonqueiroz.chatbot.response;

import com.ermesonqueiroz.chatbot.entity.ServiceEntity;
import org.springframework.stereotype.Service;

@Service
public class ServiceResponseMapper {
    public ServiceResponse fromModel(ServiceEntity service) {
        return new ServiceResponse(service.getId(), service.getName());
    }
}
