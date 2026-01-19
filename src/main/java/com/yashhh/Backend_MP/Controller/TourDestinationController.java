package com.yashhh.Backend_MP.Controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yashhh.Backend_MP.Entity.TourDestination;
import com.yashhh.Backend_MP.Service.TourDestinationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/tour-destinations")
@RequiredArgsConstructor
public class TourDestinationController {

    private final TourDestinationService service;
@PostMapping("/map")
public ResponseEntity<TourDestination> mapTour(@RequestBody Map<String, Long> ids) {
    TourDestination saved = (TourDestination) service.mapTourToDestination(ids.get("tourId"), ids.get("destinationId"));
    return ResponseEntity.ok(saved); // ✅ type matches
}
}
    


