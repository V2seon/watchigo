package com.example.watchigo.service;

import com.example.watchigo.dto.ExhibitDto;
import com.example.watchigo.dto.ServiceZoneDto;
import com.example.watchigo.entity.ExhibitEntity;
import com.example.watchigo.entity.ServiceZoneEntity;
import com.example.watchigo.repository.ExhibitRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@AllArgsConstructor
@Service
public class ExhibitService {
    private ExhibitRepository exhibitRepository;

    @Transactional
    public Long save(ExhibitDto exhibitDto){
        ExhibitEntity exhibitEntity = exhibitDto.toEntity();
        exhibitRepository.save(exhibitEntity);
        return exhibitEntity.getSeq();
    }


    @Transactional
    public Page<ExhibitEntity> selectALLTable0(Long seq, Pageable pageable){
        return exhibitRepository.findAseq(seq,pageable);
    }
}
