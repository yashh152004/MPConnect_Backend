package com.yashhh.Backend_MP.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tour_media")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TourMedia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fileName;
    private String fileType; // image/jpeg, application/pdf
    private String filePath; // saved location

    @Enumerated(EnumType.STRING)
    private MediaType mediaType; // PHOTO / NEWSPAPER

    @ManyToOne
    @JoinColumn(name = "tour_id")
    private Tour tour;
}
