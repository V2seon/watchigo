package com.example.watchigo.repository;

import com.example.watchigo.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByAidAndApw(String A_ID, String A_PW);
}
