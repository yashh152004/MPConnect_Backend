package com.yashhh.Backend_MP.Controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.yashhh.Backend_MP.Entity.DaybookAttachment;
import com.yashhh.Backend_MP.Service.DaybookAttachmentService;


@RestController
@RequestMapping("/attach/upload")
public class DaybookAttachmentController {
    private DaybookAttachmentService daybookattachment;
    public DaybookAttachmentController(DaybookAttachmentService daybookattachment)
    {
        this.daybookattachment=daybookattachment;
    }
    @PostMapping("/file")
    public ResponseEntity<String> uploadFile(
            @PathVariable Long fileName,
            @RequestParam("file") MultipartFile file) throws Exception {

        DaybookAttachmentService.uploadFile(fileName, file);
        return ResponseEntity.ok("Evidence uploaded successfully");
    }
            }