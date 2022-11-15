package com.example.watchigo.repository;

import com.example.watchigo.entity.RentangleEntiry;
import com.example.watchigo.entity.ServiceZoneEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentangleRepository  extends JpaRepository<RentangleEntiry, Long> {
}
