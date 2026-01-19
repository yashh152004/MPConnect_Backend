package com.yashhh.Backend_MP.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yashhh.Backend_MP.Entity.TourDestination;

@Repository
public interface TourDestinationRepository extends JpaRepository<TourDestination, Long> {

    List<TourDestination> findByTourId(Long tourId);
}
