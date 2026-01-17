package com.yashhh.Backend_MP.Repository;

import com.yashhh.Backend_MP.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,String> {
    Optional<User> findByName(String name);
}
