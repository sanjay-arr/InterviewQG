package com.example.exam.Entity;

import java.time.LocalDateTime;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import lombok.Data;

@Entity
@Data 
@Table(name="user_attempts")
public class UserAttempt {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long attemptId;
    
    @Column(columnDefinition = "TEXT")
    private String userAnswer;
    
    private Boolean isCorrect;
    
    private LocalDateTime attemptTime;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name="question_id")
    private Question question;
}
