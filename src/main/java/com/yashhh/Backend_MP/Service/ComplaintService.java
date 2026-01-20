package com.yashhh.Backend_MP.Service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.yashhh.Backend_MP.Entity.Complaint;
import com.yashhh.Backend_MP.Entity.ComplaintStatus;
import com.yashhh.Backend_MP.Entity.User;
import com.yashhh.Backend_MP.Repository.ComplaintRepository;

@Service
public class ComplaintService {

    private final ComplaintRepository complaintRepository;

    public ComplaintService(ComplaintRepository complaintRepository) {
        this.complaintRepository = complaintRepository;
    }

    // Citizen creates complaint
    public Complaint createComplaint(Complaint complaint, User citizen) {
        complaint.setCreatedBy(citizen);
        complaint.setStatus(ComplaintStatus.OPEN);
        complaint.setCreatedAt(LocalDateTime.now());
        return complaintRepository.save(complaint);
    }

    // Citizen view own complaints
    public List<Complaint> getComplaintsByCitizen(Long userId) {
        return complaintRepository.findByCreatedById(userId);
    }

    // Staff/MP view all complaints
    public List<Complaint> getAllComplaints() {
        return complaintRepository.findAll();
    }

    // Update status
    public Complaint updateStatus(Long id, ComplaintStatus status) {
        Complaint complaint = complaintRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Complaint not found"));
        complaint.setStatus(status);
        return complaintRepository.save(complaint);
    }
}
