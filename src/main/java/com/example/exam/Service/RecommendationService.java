package com.example.exam.Service;

import com.example.exam.Entity.Recommendation;
import com.example.exam.Entity.UserProgress;
import com.example.exam.Repository.RecommendationRepository;
import com.example.exam.Repository.UserProgressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class RecommendationService {

    @Autowired
    private RecommendationRepository recommendationRepository;

    @Autowired
    private UserProgressRepository userProgressRepository;

    public List<Recommendation> generateUserRecommendations(Long userId) {
        List<UserProgress> progressList = userProgressRepository.findByUserUserId(userId);
        List<Recommendation> newRecommendations = new ArrayList<>();

        for (UserProgress progress : progressList) {
            Double accuracy = progress.getAccuracy() != null ? progress.getAccuracy() : 0.0;
            Integer totalAttempts = progress.getTotalAttempts() != null ? progress.getTotalAttempts() : 0;

            if (accuracy < 0.6 && totalAttempts >= 2) {
                Recommendation rec = new Recommendation();
                rec.setUser(progress.getUser());
                rec.setTopic(progress.getTopic());
                rec.setSuggestionText("Your accuracy in " + progress.getTopic().getName() +
                        " is " + String.format("%.2f", accuracy * 100) + "%. " +
                        "Consider reviewing the fundamentals of this topic before trying harder questions.");
                newRecommendations.add(recommendationRepository.save(rec));
            } else if (accuracy >= 0.9 && progress.getCurrentlevel() < 3) {
                Recommendation rec = new Recommendation();
                rec.setUser(progress.getUser());
                rec.setTopic(progress.getTopic());
                rec.setSuggestionText("Great job on " + progress.getTopic().getName() + "! " +
                        "You are ready to challenge yourself with Harder questions.");
                newRecommendations.add(recommendationRepository.save(rec));
            }
        }
        return newRecommendations;
    }

    public Recommendation saveRecommendation(Recommendation recommendation) {
        return recommendationRepository.save(recommendation);
    }

    public Optional<Recommendation> getRecommendationById(Long recommendationId) {
        return recommendationRepository.findById(recommendationId);
    }

    public List<Recommendation> getAllRecommendations() {
        return recommendationRepository.findAll();
    }

    public Recommendation updateRecommendation(Recommendation recommendation) {
        return recommendationRepository.save(recommendation);
    }

    public void deleteRecommendation(Long recommendationId) {
        recommendationRepository.deleteById(recommendationId);
    }

    public List<Recommendation> getRecommendationsByUserId(Long userId) {
        return recommendationRepository.findByUserUserId(userId);
    }

    public List<Recommendation> getRecommendationsByTopicId(Long topicId) {
        return recommendationRepository.findByTopicTopicId(topicId);
    }
}