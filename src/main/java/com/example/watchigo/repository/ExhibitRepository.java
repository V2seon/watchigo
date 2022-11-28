package com.example.watchigo.repository;

import com.example.watchigo.entity.ExhibitEntity;
import com.example.watchigo.entity.ServiceZoneEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;
import java.util.Optional;

public interface ExhibitRepository extends JpaRepository<ExhibitEntity, Long>, QuerydslPredicateExecutor<ExhibitEntity> {

    @Query(value = "SELECT * FROM exhibit where userseq =:seq" , nativeQuery = true)
    Page<ExhibitEntity> findAseq(Long seq, Pageable pageable);

    Optional<ExhibitEntity> findByname(String name);

    List<ExhibitEntity> findByuserseq(Long userseq);

    @Query(value = "SELECT * FROM exhibit " , nativeQuery = true)
    Page<ExhibitEntity> findAll(Pageable pageable);


}
