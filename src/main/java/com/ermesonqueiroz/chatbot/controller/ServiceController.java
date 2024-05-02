package com.ermesonqueiroz.chatbot.controller;

import com.ermesonqueiroz.chatbot.request.CreateServiceRequest;
import com.ermesonqueiroz.chatbot.response.ServiceResponse;
import com.ermesonqueiroz.chatbot.service.ServiceService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/services")
public class ServiceController {
    private final ServiceService serviceService;

    public ServiceController(ServiceService serviceService) {
        this.serviceService = serviceService;
    }

    @PostMapping()
    public ServiceResponse createService(@RequestBody CreateServiceRequest createServiceRequest) {
        return serviceService.createService(createServiceRequest);
    }
}
