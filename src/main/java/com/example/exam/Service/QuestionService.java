package com.example.exam.Service;

import com.example.exam.Entity.Question;
import com.example.exam.Repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

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
}