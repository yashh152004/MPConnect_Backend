package com.yashhh.Backend_MP.Service;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.yashhh.Backend_MP.Entity.MediaType;
import com.yashhh.Backend_MP.Entity.Tour;
import com.yashhh.Backend_MP.Entity.TourMedia;
import com.yashhh.Backend_MP.Entity.User;
import com.yashhh.Backend_MP.Repository.TourMediaRepository;
import com.yashhh.Backend_MP.Repository.TourRepository;
import com.yashhh.Backend_MP.Repository.UserRepository;

@Service
public class TourService {

    private final TourRepository tourRepo;
    private final TourMediaRepository mediaRepo;
    private final UserRepository userRepo;

    private final String UPLOAD_DIR = "uploads/tours/";

    public TourService(TourRepository tourRepo,
            TourMediaRepository mediaRepo,
            UserRepository userRepo) {
        this.tourRepo = tourRepo;
        this.mediaRepo = mediaRepo;
        this.userRepo = userRepo;
    }

    // 🟢 Create Tour (MP / PA / STAFF)
    public Tour createTour(Tour tour) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepo.findByEmail(auth.getName()).orElseThrow();

        tour.setCreatedBy(user);
        tour.setTourDate(LocalDate.now());

        return tourRepo.save(tour);
    }

    // 📥 Retrieve all tours (used by frontend list pages)
    public java.util.List<Tour> getAllTours() {
        return tourRepo.findAll();
    }

    public java.util.Optional<Tour> getTourById(Long id) {
        return tourRepo.findById(id);
    }

    // 🟢 Upload media per tour
    public TourMedia uploadMedia(Long tourId, MultipartFile file, MediaType type) throws Exception {

        Tour tour = tourRepo.findById(tourId)
                .orElseThrow(() -> new RuntimeException("Tour not found"));

        Files.createDirectories(Paths.get(UPLOAD_DIR));

        String path = UPLOAD_DIR + System.currentTimeMillis() + "_" + file.getOriginalFilename();
        Files.copy(file.getInputStream(), Paths.get(path));

        TourMedia media = TourMedia.builder()
                .fileName(file.getOriginalFilename())
                .fileType(file.getContentType())
                .filePath(path)
                .mediaType(type)
                .tour(tour)
                .build();

        return mediaRepo.save(media);
    }
}
