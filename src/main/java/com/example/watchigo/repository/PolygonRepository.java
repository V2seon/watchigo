package com.example.watchigo.repository;

import com.example.watchigo.entity.CircleEntity;
import com.example.watchigo.entity.PolygonEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PolygonRepository extends JpaRepository<PolygonEntity, Long> {

    List<PolygonEntity> findByApk(Long pk);

    List<PolygonEntity> findByAuserseq(Long userseq);
}
