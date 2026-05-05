package com.example.exam.Service;

import com.example.exam.Entity.UserProgress;
import com.example.exam.Repository.UserProgressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserProgressService {

    @Autowired
    private UserProgressRepository userProgressRepository;

    public UserProgress saveProgress(UserProgress userProgress) {
        return userProgressRepository.save(userProgress);
    }

    public Optional<UserProgress> getProgressById(Long progressId) {
        return userProgressRepository.findById(progressId);
    }

    public List<UserProgress> getAllProgress() {
        return userProgressRepository.findAll();
    }

    public UserProgress updateProgress(UserProgress userProgress) {
        return userProgressRepository.save(userProgress);
    }

    public void deleteProgress(Long progressId) {
        userProgressRepository.deleteById(progressId);
    }

    public List<UserProgress> getProgressByUserId(Long userId) {
        return userProgressRepository.findByUserUserId(userId);
    }

    public Optional<UserProgress> getProgressByUserAndTopic(Long userId, Long topicId) {
        return userProgressRepository.findByUserUserIdAndTopicTopicId(userId, topicId);
    }
}