package com.yashhh.Backend_MP.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "complaint_evidence")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ComplaintEvidence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fileName;
    private String fileType;

    @Column(length = 1000)
    private String filePath;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "complaint_id", nullable = false)
    private Complaint complaint;
}
