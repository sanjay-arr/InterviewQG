package com.example.exam.Service;

import com.example.exam.Entity.UserAttempt;
import com.example.exam.Repository.UserAttemptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserAttemptService {

    @Autowired
    private UserAttemptRepository userAttemptRepository;

    public UserAttempt saveAttempt(UserAttempt userAttempt) {
        return userAttemptRepository.save(userAttempt);
    }

    public Optional<UserAttempt> getAttemptById(Long attemptId) {
        return userAttemptRepository.findById(attemptId);
    }

    public List<UserAttempt> getAllAttempts() {
        return userAttemptRepository.findAll();
    }

    public UserAttempt updateAttempt(UserAttempt userAttempt) {
        return userAttemptRepository.save(userAttempt);
    }

    public void deleteAttempt(Long attemptId) {
        userAttemptRepository.deleteById(attemptId);
    }

    public List<UserAttempt> getAttemptsByUserId(Long userId) {
        return userAttemptRepository.findByUserUserId(userId);
    }

    public List<UserAttempt> getAttemptsByQuestionId(Long questionId) {
        return userAttemptRepository.findByQuestionQuestionId(questionId);
    }

    public List<UserAttempt> getCorrectAttemptsByUserId(Long userId) {
        return userAttemptRepository.findByUserUserIdAndIsCorrectTrue(userId);
    }
}