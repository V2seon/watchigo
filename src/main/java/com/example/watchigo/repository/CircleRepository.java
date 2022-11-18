package com.example.watchigo.repository;

import com.example.watchigo.entity.CircleEntity;
import com.example.watchigo.entity.RentangleEntiry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CircleRepository extends JpaRepository<CircleEntity, Long> {

    List<CircleEntity> findByAuserseq(Long userseq);

}
