package com.example.id.repository;

import com.example.id.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// TODO:
//  T - Entity
//  ID - тип ключа, если примитив, то пишется Wrapper

// TODO:
//  1. У нас есть готовый бин - UserRepository
//  2. Здесь уже реализован CRUD (SimpleJpaRepository)
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    // TODO:
    //  1. Derived Queries <-
    //  2. JPQL
    //  3+.
    Optional<UserEntity> findByLogin(String login);
}
