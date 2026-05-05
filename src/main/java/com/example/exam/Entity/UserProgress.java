package com.example.exam.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="user_progress")
public class UserProgress {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long progressId;
    private Integer currentlevel = 1;
    private Double accuracy = 0.0;
    private Integer totalAttempts = 0;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name="topic_id")
    private Topic topic;
}
