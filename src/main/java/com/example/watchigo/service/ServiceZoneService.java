package com.example.watchigo.service;

import com.example.watchigo.dto.ServiceZoneDto;
import com.example.watchigo.entity.ServiceZoneEntity;
import com.example.watchigo.predicate.ServiceZonePredicate;
import com.example.watchigo.repository.ServiceZoneRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

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

    @Transactional
    public List<ServiceZoneEntity> findlist() {
        return serviceZoneRepository.findAll();
    }

    @Transactional
    public Page<ServiceZoneEntity> selectALLTable0(Long seq, Pageable pageable){
        return serviceZoneRepository.findAseq(seq,pageable);
    }

    @Transactional
    public Page <ServiceZoneEntity> selectALLTable(String selectKey,String titleText, Long seq, Pageable pageable){
        return serviceZoneRepository.findAll(ServiceZonePredicate.search(selectKey, titleText, seq),pageable);
    }
}
