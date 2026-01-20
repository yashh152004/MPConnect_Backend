package com.yashhh.Backend_MP.Controller;

import com.yashhh.Backend_MP.Entity.Complaint;
import com.yashhh.Backend_MP.Entity.User;
import com.yashhh.Backend_MP.Service.ComplaintService;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/complaints")
public class ComplaintController {

    private final ComplaintService complaintService;

    public ComplaintController(ComplaintService complaintService) {
        this.complaintService = complaintService;
    }

    // =========================================================
    // 🟢 CITIZEN: CREATE COMPLAINT
    // =========================================================
    @PreAuthorize("hasRole('CITIZEN')")
    @PostMapping
    public ResponseEntity<Complaint> createComplaint(
            @RequestBody Complaint complaint,
            @AuthenticationPrincipal User citizen) {

        return ResponseEntity.ok(
                complaintService.createComplaint(complaint, citizen)
        );
    }

    // =========================================================
    // 🟢 CITIZEN: UPLOAD EVIDENCE (PHOTO / PDF)
    // =========================================================
    @PreAuthorize("hasRole('CITIZEN')")
    @PostMapping("/{complaintId}/evidence")
    public ResponseEntity<Complaint> uploadEvidence(
            @PathVariable Long complaintId,
            @RequestParam("file") MultipartFile file) throws Exception {

        return ResponseEntity.ok(
                complaintService.uploadEvidence(complaintId, file)
        );
    }

    // =========================================================
    // 🔵 CITIZEN: VIEW OWN COMPLAINTS
    // =========================================================
    @PreAuthorize("hasRole('CITIZEN')")
    @GetMapping("/my")
    public ResponseEntity<List<Complaint>> getMyComplaints(
            @AuthenticationPrincipal User citizen) {

        return ResponseEntity.ok(
                complaintService.getComplaintsByCitizen(citizen.getId())
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

        return ResponseEntity.ok(
                complaintService.updateStatus(id, status)
        );
    }
}
