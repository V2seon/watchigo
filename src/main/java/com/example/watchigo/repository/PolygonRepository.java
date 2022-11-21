package com.example.watchigo.repository;

import com.example.watchigo.entity.CircleEntity;
import com.example.watchigo.entity.PolygonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface PolygonRepository extends JpaRepository<PolygonEntity, Long> {

    List<PolygonEntity> findByApk(Long pk);

    List<PolygonEntity> findByAuserseq(Long userseq);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM polygon where pk =:pk" , nativeQuery = true)
    Integer deleteByApk(Long pk);
}
