package com.yashhh.Backend_MP.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yashhh.Backend_MP.Entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
