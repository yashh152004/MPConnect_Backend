package com.yashhh.Backend_MP.Repository;

import org.springframework.stereotype.Repository;

import com.yashhh.Backend_MP.Entity.GreetingContact;

@Repository
public interface GreetingsRepository extends org.springframework.data.repository.Repository<GreetingContact, Long> {

    void save(GreetingContact greeting);

    GreetingContact findById(Long id);

}
