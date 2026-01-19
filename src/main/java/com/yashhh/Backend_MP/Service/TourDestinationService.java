package com.yashhh.Backend_MP.Service;

import org.springframework.stereotype.Service;

import com.yashhh.Backend_MP.Entity.Destination;
import com.yashhh.Backend_MP.Entity.Tour;
import com.yashhh.Backend_MP.Entity.TourDestination;
import com.yashhh.Backend_MP.Repository.DestinationRepository;
import com.yashhh.Backend_MP.Repository.TourDestinationRepository;
import com.yashhh.Backend_MP.Repository.TourRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TourDestinationService {

    private final TourDestinationRepository repository;
    private final TourRepository tourRepository;
    private final DestinationRepository destinationRepository;
public TourDestination mapTourToDestination(Long tourId, Long destinationId) {
    Tour tour = tourRepository.findById(tourId)
            .orElseThrow(() -> new RuntimeException("Tour not found"));
    Destination destination = destinationRepository.findById(destinationId)
            .orElseThrow(() -> new RuntimeException("Destination not found"));

    // ✅ Make sure to call build()
    TourDestination td = TourDestination.builder()
            .tour(tour)
            .destination(destination)
            .build(); // <-- MUST call build

    return repository.save(td);
}
}

