package com.example.exam.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.exam.Entity.User;
public interface UserRepository extends JpaRepository<User, Long> {
    User findByName(String name);

    Optional<User> findByEmail(String email);
}
