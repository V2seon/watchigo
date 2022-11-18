package com.example.watchigo.repository;

import com.example.watchigo.entity.ServiceZoneEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ServiceZoneServeyRepository extends JpaRepository<ServiceZoneEntity, Long> {

    List<ServiceZoneEntity> findByseq(Long seq);

}
