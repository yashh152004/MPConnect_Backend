package com.yashhh.Backend_MP.Service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Blob;
import java.time.LocalDate;
import java.util.UUID;

import javax.sql.rowset.serial.SerialBlob;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.yashhh.Backend_MP.Entity.DaybookAttachment;
import com.yashhh.Backend_MP.Entity.DaybookTask;
import com.yashhh.Backend_MP.Repository.DaybookAttachmentRepository;
import com.yashhh.Backend_MP.Repository.DaybookTaskRepository;

@Service
public class DaybookAttachmentService {

    private final DaybookAttachmentRepository attachmentRepository;
    private final DaybookTaskRepository taskRepository;

    private final String uploadDir = "uploads/";

    public DaybookAttachmentService(
            DaybookAttachmentRepository attachmentRepository,
            DaybookTaskRepository taskRepository) {
        this.attachmentRepository = attachmentRepository;
        this.taskRepository = taskRepository;
    }

    // 🔹 Upload File OR Voice Note
    public DaybookAttachment uploadAttachment(
            Long taskId,
            MultipartFile file) throws Exception {

        DaybookTask task = taskRepository.findById(taskId)
                .orElseThrow(() ->
                        new RuntimeException("Task not found"));

        // Create upload folder if not exists
        Path uploadPath = Paths.get(uploadDir);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        String contentType = file.getContentType();

        DaybookAttachment attachment = new DaybookAttachment();
        attachment.setFileName(file.getOriginalFilename());
        attachment.setFileType(contentType);
        attachment.setTask(task);
        attachment.setTaskDate(LocalDate.now());

        // 🔹 If audio → store as Blob
        if (contentType != null && contentType.startsWith("audio")) {

            byte[] bytes = file.getBytes();
            Blob blob = new SerialBlob(bytes);
            attachment.setVoice_note(blob);

        } else {
            // 🔹 Otherwise store in disk
            String uniqueFileName =
                    UUID.randomUUID() + "_" + file.getOriginalFilename();

            Path filePath = uploadPath.resolve(uniqueFileName);

            Files.copy(file.getInputStream(),
                    filePath,
                    StandardCopyOption.REPLACE_EXISTING);

            attachment.setFilePath(filePath.toString());
        }

        return attachmentRepository.save(attachment);
    }
}
