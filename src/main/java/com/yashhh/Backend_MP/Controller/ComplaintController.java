package com.yashhh.Backend_MP.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    // 🟢 CITIZEN: CREATE COMPLAINT
    // =========================================================
    @PreAuthorize("hasRole('CITIZEN')")
    @PostMapping
    public ResponseEntity<Complaint> createComplaint(
            @RequestParam Long citizenId,
            @RequestBody Complaint complaint) {

        return ResponseEntity.ok(
                complaintService.createComplaint(complaint, citizenId)
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
    // 🟢 CITIZEN: VIEW OWN COMPLAINTS
    // =========================================================
    @PreAuthorize("hasRole('CITIZEN')")
    @GetMapping("/my/{citizenId}")
    public ResponseEntity<List<Complaint>> getMyComplaints(
            @PathVariable Long citizenId) {

        return ResponseEntity.ok(
                complaintService.getComplaintsByCitizen(citizenId)
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
    @PreAuthorize("hasAnyRole('STAFF','MP')")
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
