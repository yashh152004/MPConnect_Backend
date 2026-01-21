package com.yashhh.Backend_MP.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yashhh.Backend_MP.Entity.Complaint;
import com.yashhh.Backend_MP.Entity.User;

public interface ComplaintRepository extends JpaRepository<Complaint, Long> {

    List<Complaint> findByCreatedBy(User user);
}
