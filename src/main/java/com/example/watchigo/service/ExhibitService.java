package com.example.watchigo.service;

import com.example.watchigo.entity.ExhibitEntity;
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
    public Page<ExhibitEntity> selectALLTable0(Long seq, Pageable pageable){
        return exhibitRepository.findAseq(seq,pageable);
    }
}
