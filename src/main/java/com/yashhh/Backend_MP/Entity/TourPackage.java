package com.yashhh.Backend_MP.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tour_packages")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TourPackage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String packageName;

    @Column(columnDefinition = "TEXT")
    private String description;

    // Example: 3.5 hours, 6 hours
    private Double typicalDurationHours;

    @Column(columnDefinition = "TEXT")
    private String standardActivities;

    @Column(columnDefinition = "TEXT")
    private String requiredResources;

    @ManyToOne
    @JoinColumn(name = "created_by", nullable = false)
    private User createdBy;

    private Boolean isActive = true;
}
