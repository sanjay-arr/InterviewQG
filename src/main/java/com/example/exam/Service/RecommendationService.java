package com.example.exam.Service;

import com.example.exam.Entity.Recommendation;
import com.example.exam.Repository.RecommendationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class RecommendationService {

    @Autowired
    private RecommendationRepository recommendationRepository;

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