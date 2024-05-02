package com.ermesonqueiroz.chatbot.service;

import com.ermesonqueiroz.chatbot.request.CreateServiceRequest;
import com.ermesonqueiroz.chatbot.response.ServiceResponse;

public interface ServiceService {
    ServiceResponse createService(CreateServiceRequest createServiceRequest);
}
