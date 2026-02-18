package com.yashhh.Backend_MP.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yashhh.Backend_MP.Entity.Letter;

@RestController
@RequestMapping("/api/letters")
public class LetterController {

    @GetMapping
    @PreAuthorize("hasAnyRole('MP','PA','STAFF')")
    public ResponseEntity<List<Letter>> getAll() {
        return ResponseEntity.ok(List.of());
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('MP','PA','STAFF')")
    public ResponseEntity<Letter> create(@RequestBody Letter l) {
        return ResponseEntity.ok(l);
    }
}
