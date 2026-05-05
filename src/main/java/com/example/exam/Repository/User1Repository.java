package com.example.exam.Repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.exam.Entity.User1;

public interface User1Repository extends JpaRepository<User1, Long> {
    Optional<User1> findByEmail(String email);
}
