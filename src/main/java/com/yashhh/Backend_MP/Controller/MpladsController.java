package com.yashhh.Backend_MP.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.yashhh.Backend_MP.Entity.MpladsFund;
import com.yashhh.Backend_MP.Repository.MpladsFundRepository;

@RestController
@RequestMapping("/api/mplads")
public class MpladsController {

    private final MpladsFundRepository mpladsFundRepository;

    public MpladsController(MpladsFundRepository mpladsFundRepository) {
        this.mpladsFundRepository = mpladsFundRepository;
    }

    // ✅ GET all funds
    @GetMapping
    @PreAuthorize("hasAnyRole('MP','PA','STAFF')")
    public ResponseEntity<List<MpladsFund>> getFunds() {
        return ResponseEntity.ok(mpladsFundRepository.findAll());
    }

    // ✅ POST (ADD) new fund
    @PostMapping
    @PreAuthorize("hasAnyRole('MP','PA')") // STAFF usually shouldn't add funds
    public ResponseEntity<MpladsFund> addFund(@RequestBody MpladsFund fund) {
        MpladsFund saved = mpladsFundRepository.save(fund);
        return ResponseEntity.ok(saved);
    }
}