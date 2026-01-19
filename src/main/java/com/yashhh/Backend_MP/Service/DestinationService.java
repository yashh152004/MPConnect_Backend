package com.yashhh.Backend_MP.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.yashhh.Backend_MP.Entity.Destination;
import com.yashhh.Backend_MP.Repository.DestinationRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DestinationService {

    private final DestinationRepository destinationRepository;

    public Destination addDestination(Destination destination) {
        return destinationRepository.save(destination);
    }

    public List<Destination> getAllDestinations() {
        return destinationRepository.findAll();
    }
}
