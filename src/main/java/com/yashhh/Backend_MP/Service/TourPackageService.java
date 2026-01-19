package com.yashhh.Backend_MP.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.yashhh.Backend_MP.Entity.TourPackage;
import com.yashhh.Backend_MP.Repository.TourPackageRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TourPackageService {

    private final TourPackageRepository tourPackageRepository;

    public TourPackage createTourPackage(TourPackage tourPackage) {
        return tourPackageRepository.save(tourPackage);
    }

    public List<TourPackage> getAllTourPackages() {
        return tourPackageRepository.findAll();
    }
}
