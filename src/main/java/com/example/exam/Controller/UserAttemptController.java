package com.example.exam.Controller;

import com.example.exam.Entity.UserAttempt;
import com.example.exam.Service.UserAttemptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/attempts")
public class UserAttemptController {

    @Autowired
    private UserAttemptService userAttemptService;

    @GetMapping
    public List<UserAttempt> getAllAttempts() {
        return userAttemptService.getAllAttempts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserAttempt> getAttemptById(@PathVariable Long id) {
        return userAttemptService.getAttemptById(id)
                .map(attempt -> ResponseEntity.ok(attempt))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/user/{userId}")
    public List<UserAttempt> getAttemptsByUser(@PathVariable Long userId) {
        return userAttemptService.getAttemptsByUserId(userId);
    }

    @PostMapping
    public UserAttempt createAttempt(@RequestBody UserAttempt userAttempt) {
        return userAttemptService.saveAttempt(userAttempt);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserAttempt> updateAttempt(@PathVariable Long id, @RequestBody UserAttempt userAttempt) {
        if (!userAttemptService.getAttemptById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        userAttempt.setAttemptId(id);
        return ResponseEntity.ok(userAttemptService.updateAttempt(userAttempt));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAttempt(@PathVariable Long id) {
        if (!userAttemptService.getAttemptById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        userAttemptService.deleteAttempt(id);
        return ResponseEntity.noContent().build();
    }
}