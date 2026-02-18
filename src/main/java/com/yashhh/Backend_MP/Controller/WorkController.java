package com.yashhh.Backend_MP.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yashhh.Backend_MP.Entity.Work;

@RestController
@RequestMapping("/api/works")
public class WorkController {

    // simple in-memory placeholder until real service exists
    @GetMapping
    @PreAuthorize("hasAnyRole('MP','PA','STAFF')")
    public ResponseEntity<List<Work>> getAll() {
        return ResponseEntity.ok(List.of());
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('MP','PA','STAFF')")
    public ResponseEntity<Work> create(@RequestBody Work w) {
        // TODO: implement persistence
        return ResponseEntity.ok(w);
    }
}
