package com.yashhh.Backend_MP.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.yashhh.Backend_MP.Entity.GreetingContact;
import com.yashhh.Backend_MP.Repository.GreetingsRepository;

@Service
public class GreetingServiceImpl implements GreetingService {

    private final GreetingsRepository greetingsRepository;

    public GreetingServiceImpl(GreetingsRepository greetingsRepository) {
        this.greetingsRepository = greetingsRepository;
    }

    @Override
    public List<GreetingContact> getAllGreetings() {
        return greetingsRepository.findAll();
    }

    @Override
    public GreetingContact createGreeting(GreetingContact greeting) {
        return greetingsRepository.save(greeting);
    }
}
