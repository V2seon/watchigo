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

    @Query(value = "SELECT ALV_SEQ FROM AI_LABELING_VIDEO WHERE ALV_VIDEO LIKE :videoname", nativeQuery = true)
    Long findByAlvseq(String videoname); // videoname+%

    @Modifying
    @Transactional
    @Query(value = "UPDATE AI_LABELING_VIDEO SET ALV_STATE =:state WHERE ALV_SEQ =:alvseq", nativeQuery = true)
    void updateState(Long alvseq, int state);

    @Query(value = "SELECT * FROM AI_LABELING_VIDEO WHERE ALV_U_SEQ =:useq", nativeQuery = true)
    Page<AivideoALVEntity> findALVList(Long useq, Pageable pageble);

}
