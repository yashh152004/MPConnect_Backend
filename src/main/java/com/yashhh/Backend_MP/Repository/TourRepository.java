package com.yashhh.Backend_MP.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yashhh.Backend_MP.Entity.Tour;

@Repository
public interface TourRepository extends JpaRepository<Tour, Long> {

    
}
