package com.yashhh.Backend_MP.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.yashhh.Backend_MP.Entity.*;
import com.yashhh.Backend_MP.Service.TourService;

@RestController
@RequestMapping("/api/tours")
public class TourController {

    private final TourService tourService;

    public TourController(TourService tourService) {
        this.tourService = tourService;
    }

    // 🟠 MP / PA / STAFF: Create tour
    @PreAuthorize("hasAnyRole('MP','PA','STAFF')")
    @PostMapping
    public ResponseEntity<Tour> createTour(@RequestBody Tour tour) {
        return ResponseEntity.ok(tourService.createTour(tour));
    }

    // list all tours (any authenticated role)
    @GetMapping
    public ResponseEntity<java.util.List<Tour>> getAllTours() {
        return ResponseEntity.ok(tourService.getAllTours());
    }

    // get by id
    @GetMapping("/{id}")
    public ResponseEntity<Tour> getTourById(@PathVariable Long id) {
        return tourService.getTourById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 🟠 Upload PHOTO
    @PreAuthorize("hasAnyRole('MP','PA','STAFF')")
    @PostMapping("/{tourId}/photo")
    public ResponseEntity<?> uploadPhoto(
            @PathVariable Long tourId,
            @RequestParam MultipartFile file) throws Exception {

        return ResponseEntity.ok(
                tourService.uploadMedia(tourId, file, MediaType.PHOTO));
    }

    // 🟠 Upload NEWSPAPER
    @PreAuthorize("hasAnyRole('MP','PA','STAFF')")
    @PostMapping("/{tourId}/newspaper")
    public ResponseEntity<?> uploadNewspaper(
            @PathVariable Long tourId,
            @RequestParam MultipartFile file) throws Exception {

        return ResponseEntity.ok(
                tourService.uploadMedia(tourId, file, MediaType.NEWSPAPER));
    }
}
