package com.yashhh.Backend_MP.Controller;

import com.yashhh.Backend_MP.Entity.Tour;
import com.yashhh.Backend_MP.Service.TourService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tours")
public class TourController {

    private final TourService service;

    public TourController(TourService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Tour> create(@RequestBody Tour tour) {
        return ResponseEntity.ok(service.createTour(tour));
    }

    @GetMapping
    public ResponseEntity<List<Tour>> getAll() {
        return ResponseEntity.ok(service.getAllTours());
    }
}
