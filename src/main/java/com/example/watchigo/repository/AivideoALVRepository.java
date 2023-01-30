package com.example.watchigo.repository;

import com.example.watchigo.entity.AivideoALVEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import javax.transaction.Transactional;

public interface AivideoALVRepository extends JpaRepository<AivideoALVEntity, Long>, QuerydslPredicateExecutor<AivideoALVEntity> {

    @Query(value = "SELECT * FROM AI_LABELING_VIDEO WHERE ALV_U_SEQ =:useq", nativeQuery = true)
    Page<AivideoALVEntity> findALVList(Long useq, Pageable pageble);

    @Query(value = "SELECT * FROM AI_LABELING_VIDEO WHERE ALV_SEQ =:alvseq", nativeQuery = true)
    AivideoALVEntity findALVdata(Long alvseq);

    @Query(value = "SELECT ALV_SEQ FROM AI_LABELING_VIDEO WHERE ALV_VIDEO LIKE :videoname", nativeQuery = true)
    Long findALVseq(String videoname); // videoname+%

    @Query(value = "SELECT ALV_VIDEO FROM AI_LABELING_VIDEO WHERE ALV_SEQ =:alvseq", nativeQuery = true)
    String findALVvideo(Long alvseq);

    @Query(value = "SELECT ALV_STATE FROM AI_LABELING_VIDEO WHERE ALV_SEQ =:alvseq", nativeQuery = true)
    String findALVstate(Long alvseq);

    @Modifying
    @Transactional
    @Query(value = "UPDATE AI_LABELING_VIDEO SET ALV_STATE =:state WHERE ALV_SEQ =:alvseq", nativeQuery = true)
    void updateState(Long alvseq, int state);

    @Modifying
    @Transactional
    @Query(value = "UPDATE AI_LABELING_VIDEO SET ALV_CLASS =:alvclass, ALV_NAME =:alvname WHERE ALV_SEQ =:alvseq", nativeQuery = true)
    void updateDatas(Long alvseq, String alvclass, String alvname);

    @Modifying
    @Transactional
    @Query(value = "UPDATE AI_LABELING_VIDEO SET ALV_VIDEO =:alvvideo WHERE ALV_SEQ =:alvseq", nativeQuery = true)
    void updatevideo(Long alvseq, String alvvideo);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM AI_LABELING_VIDEO WHERE ALV_SEQ =:alvseq", nativeQuery = true)
    void deleteALVData(Long alvseq);

}
