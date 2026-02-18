package com.yashhh.Backend_MP.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yashhh.Backend_MP.Entity.MpladsFund;

@RestController
@RequestMapping("/api/mplads")
public class MpladsController {
    // stub endpoints
    @GetMapping
    @PreAuthorize("hasAnyRole('MP','PA','STAFF')")
    public ResponseEntity<List<MpladsFund>> getFunds() {
        return ResponseEntity.ok(List.of());
    }
}
