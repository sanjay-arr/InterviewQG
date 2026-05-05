package com.example.exam.Controller;

import com.example.exam.Entity.UserProgress;
import com.example.exam.Service.UserProgressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/progress")
public class UserProgressController {

    @Autowired
    private UserProgressService userProgressService;

    @GetMapping
    public List<UserProgress> getAllProgress() {
        return userProgressService.getAllProgress();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserProgress> getProgressById(@PathVariable Long id) {
        return userProgressService.getProgressById(id)
                .map(progress -> ResponseEntity.ok(progress))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/user/{userId}")
    public List<UserProgress> getProgressByUser(@PathVariable Long userId) {
        return userProgressService.getProgressByUserId(userId);
    }

    @PostMapping
    public UserProgress createProgress(@RequestBody UserProgress userProgress) {
        return userProgressService.saveProgress(userProgress);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserProgress> updateProgress(@PathVariable Long id, @RequestBody UserProgress userProgress) {
        if (!userProgressService.getProgressById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        userProgress.setProgressId(id);
        return ResponseEntity.ok(userProgressService.updateProgress(userProgress));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProgress(@PathVariable Long id) {
        if (!userProgressService.getProgressById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        userProgressService.deleteProgress(id);
        return ResponseEntity.noContent().build();
    }
}