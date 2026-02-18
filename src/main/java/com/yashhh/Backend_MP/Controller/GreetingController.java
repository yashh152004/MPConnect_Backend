package com.yashhh.Backend_MP.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yashhh.Backend_MP.Entity.GreetingContact;

@RestController
@RequestMapping("/api/greetings")
public class GreetingController {

    @GetMapping
    @PreAuthorize("hasAnyRole('MP','PA','STAFF')")
    public ResponseEntity<List<GreetingContact>> getAll() {
        return ResponseEntity.ok(List.of());
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('MP','PA','STAFF')")
    public ResponseEntity<GreetingContact> create(@RequestBody GreetingContact g) {
        return ResponseEntity.ok(g);
    }
}
