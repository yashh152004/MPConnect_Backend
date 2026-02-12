package com.yashhh.Backend_MP.Service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.yashhh.Backend_MP.Entity.DaybookTask;
import com.yashhh.Backend_MP.Repository.DaybookTaskRepository;

@Service
public class VoiceSearchService {

    private final DaybookTaskRepository taskRepository;
    private final SpeechToTextService speechService;

    public VoiceSearchService(
            DaybookTaskRepository taskRepository,
            SpeechToTextService speechService) {
        this.taskRepository = taskRepository;
        this.speechService = speechService;
    }

    public List<DaybookTask> searchByVoice(MultipartFile audioFile) throws Exception {

        // 🔥 1️⃣ Convert speech to text
        String recognizedText = speechService.convertSpeechToText(audioFile);

        System.out.println("Recognized Text: " + recognizedText);

        // 🔥 2️⃣ Search DB
        return taskRepository
                .findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(
                        recognizedText,
                        recognizedText
                );
    }
}
