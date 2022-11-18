package com.example.watchigo.repository;

import com.example.watchigo.entity.RentangleEntiry;
import com.example.watchigo.entity.ServiceZoneEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;
import java.util.Optional;

public interface ServiceZoneRepository extends JpaRepository<ServiceZoneEntity, Long>, QuerydslPredicateExecutor<ServiceZoneEntity> {

    Optional<ServiceZoneEntity> findByzonename(String zonename);

    Optional<ServiceZoneEntity> findByseq(Long seq);

    @Query(value = "SELECT * FROM servicezone where seq =:seq" , nativeQuery = true)
    Page<ServiceZoneEntity> findAseq(Long seq, Pageable pageable);

    @Query(value = "SELECT * FROM servicezone " , nativeQuery = true)
    Page<ServiceZoneEntity> findAll(Pageable pageable);


}
