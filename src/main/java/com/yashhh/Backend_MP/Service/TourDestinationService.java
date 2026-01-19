package com.yashhh.Backend_MP.Service;

import org.springframework.stereotype.Service;

import com.yashhh.Backend_MP.Entity.TourDestination;
import com.yashhh.Backend_MP.Repository.TourDestinationRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TourDestinationService {

    private final TourDestinationRepository repository;

    public TourDestination mapTourToDestination(TourDestination tourDestination) {
        return repository.save(tourDestination);
    }
}
