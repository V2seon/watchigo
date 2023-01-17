package com.example.watchigo.service;

import com.example.watchigo.dto.AivideoALDDto;
import com.example.watchigo.dto.AivideoALVDto;
import com.example.watchigo.entity.AivideoALVEntity;
import com.example.watchigo.entity.AivideoALDEntity;
import com.example.watchigo.repository.AivideoALVRepository;
import com.example.watchigo.repository.AivideoALDRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@AllArgsConstructor
@Service
public class AivideoService {
    private AivideoALVRepository aivideoALVRepository;
    private AivideoALDRepository aivideoALDRepository;

    @Transactional
    public Long alvSave(AivideoALVDto aivideoALVDto){
        AivideoALVEntity aivideoALVEntity = aivideoALVDto.toEntity();
        aivideoALVRepository.save(aivideoALVEntity);
        return aivideoALVDto.getALV_SEQ();
    }
    @Transactional
    public Long aldSave(AivideoALDDto aivideoALDDto){
        AivideoALDEntity aivideoALDEntity = aivideoALDDto.toEntity();
        aivideoALDRepository.save(aivideoALDEntity);
        return aivideoALDDto.getALD_PK();
    }
}
