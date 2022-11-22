package com.example.watchigo.repository;

import com.example.watchigo.entity.ExhibitEntity;
import com.example.watchigo.entity.ServiceZoneEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ExhibitRepository extends JpaRepository<ExhibitEntity, Long> {

    @Query(value = "SELECT * FROM exhibit where userseq =:seq" , nativeQuery = true)
    Page<ExhibitEntity> findAseq(Long seq, Pageable pageable);
}
