package com.yashhh.Backend_MP.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.yashhh.Backend_MP.Service.VoiceSearchService;

@RestController
@RequestMapping("/api/daybook")
public class VoiceSearchController {

    private final VoiceSearchService voiceSearchService;

    public VoiceSearchController(VoiceSearchService voiceSearchService) {
        this.voiceSearchService = voiceSearchService;
    }

    @PostMapping("/voice-search")
    @PreAuthorize("hasAnyRole('MP','PA','STAFF')")
    public ResponseEntity<?> searchByVoice(
            @RequestParam("file") MultipartFile file) throws Exception {

        return ResponseEntity.ok(
                voiceSearchService.searchByVoice(file)
        );
    }
}
