package com.yashhh.Backend_MP.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yashhh.Backend_MP.Entity.TourPackage;
import com.yashhh.Backend_MP.Service.TourPackageService;

@RestController
@RequestMapping("/api/tour-packages")
public class TourPackageController {

    private final TourPackageService service;

    public TourPackageController(TourPackageService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<TourPackage> create(@RequestBody TourPackage tourPackage) {
        return ResponseEntity.ok(service.createTourPackage(tourPackage));
    }

    @GetMapping
    public ResponseEntity<List<TourPackage>> getAll() {
        return ResponseEntity.ok(service.getAllTourPackages());
    }
}
