package com.example.watchigo.service;

import com.example.watchigo.dto.ServiceZoneDto;
import com.example.watchigo.entity.ServiceZoneEntity;
import com.example.watchigo.repository.ServiceZoneRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@AllArgsConstructor
@Service
public class ServiceZoneService {
    private ServiceZoneRepository serviceZoneRepository;

    @Transactional
    public Long save(ServiceZoneDto serviceZoneDto){
        ServiceZoneEntity serviceZoneEntity = serviceZoneDto.toEntity();
        serviceZoneRepository.save(serviceZoneEntity);
        return serviceZoneDto.getA_pk();
    }
}
