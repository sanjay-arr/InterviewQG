package com.example.exam.Repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.exam.Entity.UserAttempt;

public interface UserAttemptRepository extends JpaRepository<UserAttempt, Long> {
    List<UserAttempt> findByUserUserId(Long userId);

    List<UserAttempt> findByQuestionQuestionId(Long questionId);

    List<UserAttempt> findByUserUserIdAndIsCorrectTrue(Long userId);
}