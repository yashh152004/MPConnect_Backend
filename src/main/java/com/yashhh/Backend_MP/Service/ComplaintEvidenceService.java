package com.yashhh.Backend_MP.Service;

import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.yashhh.Backend_MP.Entity.Complaint;
import com.yashhh.Backend_MP.Entity.ComplaintEvidence;
import com.yashhh.Backend_MP.Repository.ComplaintEvidenceRepository;

@Service
public class ComplaintEvidenceService {

    private final ComplaintEvidenceRepository repository;

    private final String UPLOAD_DIR = "uploads/complaints/";

    public ComplaintEvidenceService(ComplaintEvidenceRepository repository) {
        this.repository = repository;
    }

    public ComplaintEvidence uploadEvidence(Complaint complaint, MultipartFile file) throws Exception {

        Files.createDirectories(Paths.get(UPLOAD_DIR));

        String filePath = UPLOAD_DIR + System.currentTimeMillis() + "_" + file.getOriginalFilename();
        Files.copy(file.getInputStream(), Paths.get(filePath));

        ComplaintEvidence evidence = ComplaintEvidence.builder()
                .fileName(file.getOriginalFilename())
                .fileType(file.getContentType())
                .filePath(filePath)
                .complaint(complaint)
                .build();

        return repository.save(evidence);
    }
}
