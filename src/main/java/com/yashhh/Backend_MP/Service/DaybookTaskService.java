package com.yashhh.Backend_MP.Service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.yashhh.Backend_MP.Entity.DaybookTask;
import com.yashhh.Backend_MP.Entity.User;
import com.yashhh.Backend_MP.Repository.DaybookTaskRepository;
import com.yashhh.Backend_MP.Repository.UserRepository;
@Service
public class DaybookTaskService {

    private final DaybookTaskRepository daybookRepository;
    private final UserRepository userRepository;

    public DaybookTaskService(DaybookTaskRepository daybookRepository,
                              UserRepository userRepository) {
        this.daybookRepository = daybookRepository;
        this.userRepository = userRepository;
    }

    // ✅ CREATE DAYBOOK TASK (JWT EMAIL)
    public DaybookTask createTask(DaybookTask task, String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        task.setCreatedBy(user);
        task.setCreatedAt(LocalDateTime.now());

        return daybookRepository.save(task);
    }

    // ✅ VIEW ALL TASKS
    public List<DaybookTask> getAllTasks() {
        return daybookRepository.findAll();
    }

    // ✅ VIEW TASKS BY USER (AUDIT)
    public List<DaybookTask> getTasksByUser(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return daybookRepository.findByCreatedBy(user);
    }
}
