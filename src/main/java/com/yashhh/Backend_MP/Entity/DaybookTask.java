package com.yashhh.Backend_MP.Entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "daybook_tasks")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DaybookTask {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Example: "Village Water Inspection"
    private String title;

    @Column(length = 3000)
    private String description;

    // Date for which task is planned
    private LocalDate taskDate;

    // Audit timestamps
    private LocalDateTime createdAt;

    // Who created the task (STAFF / PA / MP)
    @ManyToOne
    @JoinColumn(name = "created_by")
    private User createdBy;

    // Attachments (photo / pdf / audio) — next step
    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL)
    private List<DaybookAttachment> attachments;
}
