package com.example.watchigo.repository;

import com.example.watchigo.entity.PolygonEntity;
import com.example.watchigo.entity.RentangleEntiry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RentangleRepository  extends JpaRepository<RentangleEntiry, Long> {

    List<RentangleEntiry> findByAuserseq(Long userseq);

}
