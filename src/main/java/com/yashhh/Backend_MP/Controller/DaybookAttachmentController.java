package com.yashhh.Backend_MP.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.yashhh.Backend_MP.Entity.DaybookAttachment;
import com.yashhh.Backend_MP.Service.DaybookAttachmentService;

@RestController
@RequestMapping("/api/daybook")
public class DaybookAttachmentController {

    private final DaybookAttachmentService daybookAttachmentService;

    public DaybookAttachmentController(
            DaybookAttachmentService daybookAttachmentService) {
        this.daybookAttachmentService = daybookAttachmentService;
    }

    // 🔹 Upload attachment for a task
    @PostMapping("/attachment/{taskId}")
    @PreAuthorize("hasAnyRole('MP','PA','STAFF')")
    public ResponseEntity<DaybookAttachment> uploadFile(
            @PathVariable Long taskId,
            @RequestParam("file") MultipartFile file) throws Exception {

        DaybookAttachment savedAttachment =
                daybookAttachmentService.uploadAttachment(taskId, file);

        return ResponseEntity.ok(savedAttachment);
    }
}
