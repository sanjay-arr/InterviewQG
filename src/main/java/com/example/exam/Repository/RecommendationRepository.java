package com.example.exam.Repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.exam.Entity.Recommendation;

public interface RecommendationRepository extends JpaRepository<Recommendation, Long> {
    List<Recommendation> findByUserUserId(Long userId);

    List<Recommendation> findByTopicTopicId(Long topicId);
}