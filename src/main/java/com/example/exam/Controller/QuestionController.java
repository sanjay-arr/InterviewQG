package com.example.exam.Controller;

import com.example.exam.Entity.Question;
import com.example.exam.Service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/questions")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping
    public List<Question> getAllQuestions() {
        return questionService.getAllQuestions();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Question> getQuestionById(@PathVariable Long id) {
        return questionService.getQuestionById(id)
                .map(question -> ResponseEntity.ok(question))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/topic/{topicId}")
    public List<Question> getQuestionsByTopic(@PathVariable Long topicId) {
        return questionService.getQuestionsByTopicId(topicId);
    }

    @GetMapping("/adaptive/{userId}/{topicId}")
    public ResponseEntity<Question> getAdaptiveQuestion(@PathVariable Long userId, @PathVariable Long topicId) {
        try {
            return ResponseEntity.ok(questionService.getAdaptiveQuestion(userId, topicId));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public Question createQuestion(@RequestBody Question question) {
        return questionService.saveQuestion(question);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Question> updateQuestion(@PathVariable Long id, @RequestBody Question question) {
        if (!questionService.getQuestionById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        question.setQuestionId(id);
        return ResponseEntity.ok(questionService.updateQuestion(question));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuestion(@PathVariable Long id) {
        if (!questionService.getQuestionById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        questionService.deleteQuestion(id);
        return ResponseEntity.noContent().build();
    }
}