package com.yashhh.Backend_MP.Controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/live")
public class LiveLocationController {
    // return dummy geographic coordinates or status
    @GetMapping("/location")
    public ResponseEntity<Map<String, Object>> getLocation() {
        return ResponseEntity.ok(Map.of("lat", 28.6139, "lng", 77.2090));
    }
}
