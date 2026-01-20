package com.yashhh.Backend_MP.Service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.yashhh.Backend_MP.Entity.Complaint;
import com.yashhh.Backend_MP.Entity.ComplaintStatus;
import com.yashhh.Backend_MP.Entity.User;
import com.yashhh.Backend_MP.Repository.ComplaintRepository;
import com.yashhh.Backend_MP.Repository.UserRepository;

@Service
public class ComplaintService {

    private final ComplaintRepository complaintRepository;
    private final UserRepository userRepository;   // ✅ ADD THIS

    public ComplaintService(ComplaintRepository complaintRepository,
                            UserRepository userRepository) {
        this.complaintRepository = complaintRepository;
        this.userRepository = userRepository;
    }

    // ===================================================
    // Citizen creates complaint
    // ===================================================
    public Complaint createComplaint(Complaint complaint, Long citizenId) {

        User citizen = userRepository.findById(citizenId)
                .orElseThrow(() -> new RuntimeException("Citizen not found"));

        complaint.setCreatedBy(citizen);
        complaint.setStatus(ComplaintStatus.OPEN);
        complaint.setCreatedAt(LocalDateTime.now());

        return complaintRepository.save(complaint);
    }

    // ===================================================
    // Upload evidence (photo / pdf)
    // ===================================================
    public Complaint uploadEvidence(Long complaintId, MultipartFile file) throws Exception {

        Complaint complaint = complaintRepository.findById(complaintId)
                .orElseThrow(() -> new RuntimeException("Complaint not found"));

        complaint.setEvidence(file.getBytes());
        complaint.setEvidenceType(file.getContentType());

        return complaintRepository.save(complaint);
    }

    // ===================================================
    // Citizen views own complaints
    // ===================================================
    public List<Complaint> getComplaintsByCitizen(Long userId) {
        return complaintRepository.findByCreatedById(userId);
    }

    // ===================================================
    // MP / STAFF view all complaints
    // ===================================================
    public List<Complaint> getAllComplaints() {
        return complaintRepository.findAll();
    }

    // ===================================================
    // Update complaint status
    // ===================================================
    public Complaint updateStatus(Long id, ComplaintStatus status) {

        Complaint complaint = complaintRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Complaint not found"));

        complaint.setStatus(status);
        return complaintRepository.save(complaint);
    }
}
