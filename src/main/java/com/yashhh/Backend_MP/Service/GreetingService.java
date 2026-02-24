package com.yashhh.Backend_MP.Service;

import java.util.List;

import com.yashhh.Backend_MP.Entity.GreetingContact;

public interface GreetingService {

    List<GreetingContact> getAllGreetings();

    GreetingContact createGreeting(GreetingContact greeting);
}
