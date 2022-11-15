package com.example.watchigo.repository;

import com.example.watchigo.entity.ServiceZoneEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ServiceZoneRepository extends JpaRepository<ServiceZoneEntity, Long> {

    Optional<ServiceZoneEntity> findByzonename(String zonename);
}
