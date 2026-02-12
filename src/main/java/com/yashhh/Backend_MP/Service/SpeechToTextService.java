package com.yashhh.Backend_MP.Service;

import java.io.IOException;

import org.apache.tomcat.websocket.WsFrameClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class SpeechToTextService {

    @Value("${openai.api.key}")
    private String openAiKey;

    private final WsFrameClient webClient = WebClient.builder()
            .baseUrl("https://api.openai.com/v1")
            .build();

    public String convertSpeechToText(MultipartFile file) throws IOException {

        ByteArrayResource resource = new ByteArrayResource(file.getBytes()) {
            @Override
            public String getFilename() {
                return file.getOriginalFilename();
            }
        };

        return webClient.post()
                .uri("/audio/transcriptions")
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + openAiKey)
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .bodyValue(
                        MultipartBodyBuilderHelper.build(resource)
                )
                .retrieve()
                .bodyToMono(String.class)
                .map(response -> {
                    // Extract "text" field manually
                    int start = response.indexOf("\"text\":\"") + 8;
                    int end = response.indexOf("\"", start);
                    return response.substring(start, end);
                })
                .block();
    }
}
