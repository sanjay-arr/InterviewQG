package com.example.exam.Controller;

import com.example.exam.Entity.Recommendation;
import com.example.exam.Service.RecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/recommendations")
public class RecommendationController {

    @Autowired
    private RecommendationService recommendationService;

    @GetMapping
    public List<Recommendation> getAllRecommendations() {
        return recommendationService.getAllRecommendations();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Recommendation> getRecommendationById(@PathVariable Long id) {
        return recommendationService.getRecommendationById(id)
                .map(recommendation -> ResponseEntity.ok(recommendation))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/user/{userId}")
    public List<Recommendation> getRecommendationsByUser(@PathVariable Long userId) {
        return recommendationService.getRecommendationsByUserId(userId);
    }

    @GetMapping("/generate/{userId}")
    public List<Recommendation> generateRecommendations(@PathVariable Long userId) {
        return recommendationService.generateUserRecommendations(userId);
    }

    @PostMapping
    public Recommendation createRecommendation(@RequestBody Recommendation recommendation) {
        return recommendationService.saveRecommendation(recommendation);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Recommendation> updateRecommendation(@PathVariable Long id, @RequestBody Recommendation recommendation) {
        if (!recommendationService.getRecommendationById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        recommendation.setRecommendationId(id);
        return ResponseEntity.ok(recommendationService.updateRecommendation(recommendation));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRecommendation(@PathVariable Long id) {
        if (!recommendationService.getRecommendationById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        recommendationService.deleteRecommendation(id);
        return ResponseEntity.noContent().build();
    }
}