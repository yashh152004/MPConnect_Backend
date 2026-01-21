package com.yashhh.Backend_MP.Controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.yashhh.Backend_MP.Entity.Complaint;
import com.yashhh.Backend_MP.Entity.ComplaintStatus;
import com.yashhh.Backend_MP.Service.ComplaintService;

@RestController
@RequestMapping("/api/complaints")
public class ComplaintController {

    private final ComplaintService complaintService;

    public ComplaintController(ComplaintService complaintService) {
        this.complaintService = complaintService;
    }

    // =========================================================
    // 🟢 CITIZEN: CREATE COMPLAINT (JWT BASED)
    // =========================================================
    @PreAuthorize("hasRole('CITIZEN')")
    @PostMapping
    public ResponseEntity<Complaint> createComplaint(
            @RequestBody Complaint complaint,
            Authentication authentication) {

        String email = authentication.getName();

        return ResponseEntity.ok(
                complaintService.createComplaintForLoggedInCitizen(complaint, email)
        );
    }

    // =========================================================
    // 🟢 CITIZEN: UPLOAD EVIDENCE (PHOTO / PDF / NEWSPAPER)
    // =========================================================
    @PreAuthorize("hasRole('CITIZEN')")
    @PostMapping(
        value = "/{complaintId}/evidence",
        consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public ResponseEntity<String> uploadEvidence(
            @PathVariable Long complaintId,
            @RequestParam("file") MultipartFile file) throws Exception {

        complaintService.uploadEvidence(complaintId, file);
        return ResponseEntity.ok("Evidence uploaded successfully");
    }

    // =========================================================
    // 🟢 CITIZEN: VIEW OWN COMPLAINTS
    // =========================================================
    @PreAuthorize("hasRole('CITIZEN')")
    @GetMapping("/my")
    public ResponseEntity<List<Complaint>> getMyComplaints(
            Authentication authentication) {

        String email = authentication.getName();

        return ResponseEntity.ok(
                complaintService.getComplaintsForLoggedInCitizen(email)
        );
    }

    // =========================================================
    // 🟠 MP / PA / STAFF: VIEW ALL COMPLAINTS
    // =========================================================
    @PreAuthorize("hasAnyRole('MP','PA','STAFF')")
    @GetMapping
    public ResponseEntity<List<Complaint>> getAllComplaints() {
        return ResponseEntity.ok(
                complaintService.getAllComplaints()
        );
    }

    // =========================================================
    // 🟠 MP / STAFF: UPDATE COMPLAINT STATUS
    // =========================================================
    @PreAuthorize("hasAnyRole('MP','STAFF')")
    @PutMapping("/{id}/status")
    public ResponseEntity<Complaint> updateStatus(
            @PathVariable Long id,
            @RequestParam String status) {

        ComplaintStatus complaintStatus;

        try {
            complaintStatus = ComplaintStatus.valueOf(status.toUpperCase());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(
                complaintService.updateStatus(id, complaintStatus)
        );
    }
}
