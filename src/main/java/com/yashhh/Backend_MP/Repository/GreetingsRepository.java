package com.yashhh.Backend_MP.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yashhh.Backend_MP.Entity.GreetingContact;

@Repository
public interface GreetingsRepository extends JpaRepository<GreetingContact, Long> {
}
