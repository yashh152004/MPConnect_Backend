package com.yashhh.Backend_MP.Repository;

import java.io.File;

import org.springframework.data.jpa.repository.JpaRepository;
public interface  DaybookAttachmentRepository extends JpaRepository<File,Long> {
   
    
}
