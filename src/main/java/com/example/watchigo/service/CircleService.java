package com.example.watchigo.service;

import com.example.watchigo.dto.CircleDto;
import com.example.watchigo.repository.CircleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@AllArgsConstructor
@Service
public class CircleService {

    private CircleRepository circleRepository;

    @Transactional
    public Long save(CircleDto circleDto){
        return circleRepository.save(circleDto.toEntity()).getApk();
    }

}
