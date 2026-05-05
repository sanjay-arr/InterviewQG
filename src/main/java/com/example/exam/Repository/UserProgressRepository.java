package com.example.exam.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.exam.Entity.UserProgress;

public interface UserProgressRepository extends JpaRepository<UserProgress, Long> {
    List<UserProgress> findByUserUserId(Long userId);

    Optional<UserProgress> findByUserUserIdAndTopicTopicId(Long userId, Long topicId);
}