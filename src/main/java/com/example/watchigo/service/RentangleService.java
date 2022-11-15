package com.example.watchigo.service;


import com.example.watchigo.dto.RectangleDto;
import com.example.watchigo.repository.RentangleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@AllArgsConstructor
@Service
public class RentangleService {

    private RentangleRepository rentangleRepository;

    @Transactional
    public Long save(RectangleDto rectangleDto){
        return rentangleRepository.save(rectangleDto.toEntity()).getApk();
    }
}
