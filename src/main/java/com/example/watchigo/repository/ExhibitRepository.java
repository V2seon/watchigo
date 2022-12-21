package com.example.watchigo.repository;

import com.example.watchigo.entity.ExhibitEntity;
import com.example.watchigo.entity.ServiceZoneEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface ExhibitRepository extends JpaRepository<ExhibitEntity, Long>, QuerydslPredicateExecutor<ExhibitEntity> {

    @Query(value = "SELECT * FROM exhibit where userseq =:seq" , nativeQuery = true)
    Page<ExhibitEntity> findAseq(Long seq, Pageable pageable);

    Optional<ExhibitEntity> findByname(String name);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM exhibit WHERE pk=:pk", nativeQuery = true)
    Integer deleteBypk(Long pk);

    List<ExhibitEntity> findByuserseq(Long userseq);

    @Query(value = "SELECT * FROM exhibit " , nativeQuery = true)
    Page<ExhibitEntity> findAll(Pageable pageable);

    @Query(value = "SELECT * FROM exhibit where userseq =:seq and pk=:pk" , nativeQuery = true)
    Page<ExhibitEntity> findAseqApk(Long seq, Long pk, Pageable pageable);

}
