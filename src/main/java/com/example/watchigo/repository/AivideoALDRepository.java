package com.example.watchigo.repository;

import com.example.watchigo.entity.AivideoALDEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import javax.transaction.Transactional;

public interface AivideoALDRepository extends JpaRepository<AivideoALDEntity, Long>, QuerydslPredicateExecutor<AivideoALDEntity> {

    @Query(value = "SELECT * FROM AI_LABELING_DATA WHERE ALD_ALV_SEQ =:alvseq", nativeQuery = true)
    AivideoALDEntity findALDdata(Long alvseq);

    @Modifying
    @Transactional
    @Query(value = "UPDATE AI_LABELING_DATA SET ALD_LABELNAME =:labelname, ALD_MAINBOX =:mainbox WHERE ALD_ALV_SEQ =:alvseq", nativeQuery = true)
    void updateALDData(Long alvseq, String labelname, String mainbox);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM AI_LABELING_DATA WHERE ALD_ALV_SEQ =:alvseq", nativeQuery = true)
    void deleteALDData(Long alvseq);
}
