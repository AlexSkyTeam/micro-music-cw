package com.example.id.repository;

import com.example.id.entity.TokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.Instant;

public interface TokenRepository extends JpaRepository<TokenEntity, String> {
    void deleteByExpireBefore(final Instant instant);
}
