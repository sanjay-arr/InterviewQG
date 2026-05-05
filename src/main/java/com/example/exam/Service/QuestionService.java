package com.example.exam.Service;

import com.example.exam.Entity.Question;
import com.example.exam.Entity.UserProgress;
import com.example.exam.Repository.QuestionRepository;
import com.example.exam.Repository.UserProgressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@Transactional
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private UserProgressRepository userProgressRepository;

    public Question saveQuestion(Question question) {
        return questionRepository.save(question);
    }

    public Optional<Question> getQuestionById(Long questionId) {
        return questionRepository.findById(questionId);
    }

    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    public Question updateQuestion(Question question) {
        return questionRepository.save(question);
    }

    public void deleteQuestion(Long questionId) {
        questionRepository.deleteById(questionId);
    }

    public List<Question> getQuestionsByTopicId(Long topicId) {
        return questionRepository.findByTopicTopicId(topicId);
    }

    public List<Question> getQuestionsByTopicAndDifficulty(Long topicId, String difficulty) {
        return questionRepository.findByTopicTopicIdAndDifficulty(topicId, difficulty);
    }

    public Question getAdaptiveQuestion(Long userId, Long topicId) {
        Optional<UserProgress> progressOpt = userProgressRepository.findByUserUserIdAndTopicTopicId(userId, topicId);
        
        String difficulty = "Easy"; 
        if (progressOpt.isPresent()) {
            int level = progressOpt.get().getCurrentlevel();
            if (level == 2) difficulty = "Medium";
            if (level == 3) difficulty = "Hard";
        }

        List<Question> availableQuestions = questionRepository.findByTopicTopicIdAndDifficulty(topicId, difficulty);
        
        if (availableQuestions.isEmpty()) {
            availableQuestions = questionRepository.findByTopicTopicId(topicId);
        }

        if (availableQuestions.isEmpty()) {
            throw new RuntimeException("No questions found for this topic.");
        }

        return availableQuestions.get(new Random().nextInt(availableQuestions.size()));
    }
}