package com.yashhh.Backend_MP.Controller;

import java.security.Principal;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yashhh.Backend_MP.Entity.DaybookTask;
import com.yashhh.Backend_MP.Service.DaybookTaskService;

@RestController
@RequestMapping("/api/daybook")
@PreAuthorize("hasAnyRole('MP','PA','STAFF')")
public class DaybookTaskController {

    private final DaybookTaskService daybookService;

    public DaybookTaskController(DaybookTaskService daybookService) {
        this.daybookService = daybookService;
    }

    // CREATE TASK (current logged-in user)
    @PostMapping("/create")
    public ResponseEntity<DaybookTask> createTask(
            @RequestBody DaybookTask task,
            Principal principal) {

        return ResponseEntity.ok(
                daybookService.createTask(task, principal.getName())
        );
    }
   
    // VIEW ALL TASKS
    @GetMapping("/all")
    public ResponseEntity<List<DaybookTask>> getAllTasks() {
        return ResponseEntity.ok(daybookService.getAllTasks());
    }

    // VIEW BY USER
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<DaybookTask>> getTasksByUser(
            @PathVariable Long userId) {

        return ResponseEntity.ok(
                daybookService.getTasksByUser(userId)
        );
    }
}
