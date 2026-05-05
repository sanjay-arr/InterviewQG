package com.example.exam.Service;

import com.example.exam.Entity.UserAttempt;
import com.example.exam.Entity.UserProgress;
import com.example.exam.Entity.Question;
import com.example.exam.Repository.UserAttemptRepository;
import com.example.exam.Repository.UserProgressRepository;
import com.example.exam.Repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserAttemptService {

    @Autowired
    private UserAttemptRepository userAttemptRepository;

    @Autowired
    private UserProgressRepository userProgressRepository;

    @Autowired
    private QuestionRepository questionRepository;

    public UserAttempt saveAttempt(UserAttempt userAttempt) {
        Question question = questionRepository.findById(userAttempt.getQuestion().getQuestionId())
                .orElseThrow(() -> new RuntimeException("Question not found"));

        boolean isCorrect = question.getCorrectAnswer().equalsIgnoreCase(userAttempt.getUserAnswer().trim());
        userAttempt.setIsCorrect(isCorrect);
        userAttempt.setAttemptTime(LocalDateTime.now());

        UserAttempt savedAttempt = userAttemptRepository.save(userAttempt);

        updateUserProgress(userAttempt.getUser().getUserId(), question.getTopic().getTopicId(), isCorrect);

        return savedAttempt;
    }

    private void updateUserProgress(Long userId, Long topicId, boolean isCorrect) {
        UserProgress progress = userProgressRepository.findByUserUserIdAndTopicTopicId(userId, topicId)
                .orElseGet(() -> {
                    UserProgress newProgress = new UserProgress();
                    newProgress.setUser(new com.example.exam.Entity.User());
                    newProgress.getUser().setUserId(userId);
                    newProgress.setTopic(new com.example.exam.Entity.Topic());
                    newProgress.getTopic().setTopicId(topicId);
                    newProgress.setCurrentlevel(1);
                    newProgress.setTotalAttempts(0);
                    newProgress.setAccuracy(0.0);
                    return newProgress;
                });

        progress.setTotalAttempts(progress.getTotalAttempts() + 1);

        double currentAccuracy = progress.getAccuracy();
        double newAccuracy = ((currentAccuracy * (progress.getTotalAttempts() - 1)) + (isCorrect ? 1.0 : 0.0))
                / progress.getTotalAttempts();
        progress.setAccuracy(newAccuracy);

        if (progress.getTotalAttempts() >= 3) {
            if (newAccuracy >= 0.8 && progress.getCurrentlevel() < 3) {
                progress.setCurrentlevel(progress.getCurrentlevel() + 1);
            } else if (newAccuracy < 0.4 && progress.getCurrentlevel() > 1) {
                progress.setCurrentlevel(progress.getCurrentlevel() - 1);
            }
        }

        userProgressRepository.save(progress);
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