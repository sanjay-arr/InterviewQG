package com.example.exam.Repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.exam.Entity.Question;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    List<Question> findByTopicTopicId(Long topicId);

    List<Question> findByTopicTopicIdAndDifficulty(Long topicId, String difficulty);
}