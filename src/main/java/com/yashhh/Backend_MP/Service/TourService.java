package com.yashhh.Backend_MP.Service;

import org.springframework.stereotype.Service;

import com.yashhh.Backend_MP.Entity.Tour;
import com.yashhh.Backend_MP.Entity.TourDestination;
import com.yashhh.Backend_MP.Repository.TourDestinationRepository;
import com.yashhh.Backend_MP.Repository.TourRepository;

@Service
public class TourService {

    private final TourRepository tourRepository;
    private final TourDestinationRepository tourDestinationRepository;

    public TourService(TourRepository tourRepository,
                       TourDestinationRepository tourDestinationRepository) {
        this.tourRepository = tourRepository;
        this.tourDestinationRepository = tourDestinationRepository;
    }

    // ✅ MAP TOUR TO DESTINATION
    public TourDestination mapTourToDestination(TourDestination tourDestination) {
        return tourDestinationRepository.save(tourDestination);
    }

    // ✅ CREATE TOUR
    public Tour createTour(Tour tour) {
        return tourRepository.save(tour);
    }

    // ✅ GET ALL TOURS
    public java.util.List<Tour> getAllTours() {
        return tourRepository.findAll();
    }
}