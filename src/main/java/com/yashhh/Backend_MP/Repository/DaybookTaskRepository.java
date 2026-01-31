package com.yashhh.Backend_MP.Repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yashhh.Backend_MP.Entity.DaybookTask;
import com.yashhh.Backend_MP.Entity.User;

public interface DaybookTaskRepository extends JpaRepository<DaybookTask, Long> {

    // View tasks created by a specific user
    List<DaybookTask> findByCreatedBy(User user);

    // View tasks for a particular date
    List<DaybookTask> findByTaskDate(LocalDate taskDate);
}
