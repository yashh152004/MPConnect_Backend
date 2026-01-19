package com.yashhh.Backend_MP.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yashhh.Backend_MP.Entity.Destination;
import com.yashhh.Backend_MP.Service.DestinationService;

@RestController
@RequestMapping("/api/destinations")
public class DestinationController {

    private final DestinationService service;

    public DestinationController(DestinationService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Destination> create(@RequestBody Destination destination) {
        return ResponseEntity.ok(service.addDestination(destination));
    }

    @GetMapping
    public ResponseEntity<List<Destination>> getAll() {
        return ResponseEntity.ok(service.getAllDestinations());
    }
}
