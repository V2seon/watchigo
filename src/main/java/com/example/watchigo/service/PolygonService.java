package com.example.watchigo.service;

import com.example.watchigo.dto.PolygonDto;
import com.example.watchigo.repository.PolygonRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@AllArgsConstructor
@Service
public class PolygonService {

    private PolygonRepository polygonRepository;

    @Transactional
    public Long save(PolygonDto polygonDto){
        return polygonRepository.save(polygonDto.toEntity()).getApk();
    }
}
