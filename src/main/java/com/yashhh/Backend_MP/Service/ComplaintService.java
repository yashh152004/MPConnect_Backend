package com.yashhh.Backend_MP.Service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.yashhh.Backend_MP.Entity.Complaint;
import com.yashhh.Backend_MP.Entity.ComplaintStatus;
import com.yashhh.Backend_MP.Entity.User;
import com.yashhh.Backend_MP.Repository.ComplaintRepository;
import com.yashhh.Backend_MP.Repository.UserRepository;

@Service
public class ComplaintService {

    private final ComplaintRepository complaintRepository;
    private final UserRepository userRepository;
    private final ComplaintEvidenceService evidenceService;

    public ComplaintService(
            ComplaintRepository complaintRepository,
            UserRepository userRepository,
            ComplaintEvidenceService evidenceService) {

        this.complaintRepository = complaintRepository;
        this.userRepository = userRepository;
        this.evidenceService = evidenceService;
    }

    // =========================================================
    // CREATE COMPLAINT (LOGGED-IN CITIZEN)
    // =========================================================
    public Complaint createComplaintForLoggedInCitizen(
            Complaint complaint, String email) {

        User citizen = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Citizen not found"));

        complaint.setCreatedBy(citizen);
        complaint.setStatus(ComplaintStatus.OPEN);
        complaint.setCreatedAt(LocalDateTime.now());

        return complaintRepository.save(complaint);
    }

    // =========================================================
    // UPLOAD EVIDENCE
    // =========================================================
    @Transactional
    public void uploadEvidence(Long complaintId, MultipartFile file) throws Exception {

        Complaint complaint = complaintRepository.findById(complaintId)
                .orElseThrow(() -> new RuntimeException("Complaint not found"));

        evidenceService.uploadEvidence(complaint, file);
    }

    // =========================================================
    // GET LOGGED-IN CITIZEN COMPLAINTS
    // =========================================================
    public List<Complaint> getComplaintsForLoggedInCitizen(String email) {

        User citizen = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Citizen not found"));

return complaintRepository.findByCreatedBy(citizen);
    }

    // =========================================================
    // GET ALL COMPLAINTS
    // =========================================================
    public List<Complaint> getAllComplaints() {
        return complaintRepository.findAll();
    }

    // =========================================================
    // UPDATE STATUS
    // =========================================================
    public Complaint updateStatus(Long id, ComplaintStatus status) {

        Complaint complaint = complaintRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Complaint not found"));

        complaint.setStatus(status);
        return complaintRepository.save(complaint);
    }
}
