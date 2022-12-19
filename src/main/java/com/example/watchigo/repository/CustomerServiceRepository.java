package com.example.watchigo.repository;

import com.example.watchigo.entity.CustomerServiceEntity;
import com.example.watchigo.entity.ExhibitEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface CustomerServiceRepository extends JpaRepository<CustomerServiceEntity, Long>, QuerydslPredicateExecutor<CustomerServiceEntity> {

    @Query(value = "SELECT * FROM customerservice where type =:type" , nativeQuery = true)
    Page<CustomerServiceEntity> findAtype(String type, Pageable pageable);

}
