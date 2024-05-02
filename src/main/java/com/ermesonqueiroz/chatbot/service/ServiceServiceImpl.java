package com.ermesonqueiroz.chatbot.service;

import com.ermesonqueiroz.chatbot.entity.ServiceEntity;
import com.ermesonqueiroz.chatbot.repository.ServiceRepository;
import com.ermesonqueiroz.chatbot.request.CreateServiceRequest;
import com.ermesonqueiroz.chatbot.response.ServiceResponse;
import com.ermesonqueiroz.chatbot.response.ServiceResponseMapper;
import org.springframework.stereotype.Service;

@Service
public class ServiceServiceImpl implements ServiceService {
    private final ServiceRepository serviceRepository;
    private final ServiceResponseMapper serviceResponseMapper;

    public ServiceServiceImpl(ServiceRepository serviceRepository, ServiceResponseMapper serviceResponseMapper) {
        this.serviceRepository = serviceRepository;
        this.serviceResponseMapper = serviceResponseMapper;
    }

    @Override
    public ServiceResponse createService(CreateServiceRequest createServiceRequest) {
        ServiceEntity service = new ServiceEntity(createServiceRequest.name());
        serviceRepository.save(service);

        return serviceResponseMapper.fromModel(service);
    }
}
