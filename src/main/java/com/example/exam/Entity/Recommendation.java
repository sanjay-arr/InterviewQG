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
@Table(name="recommendations")
public class Recommendation {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long recommendationId;
    private String suggestionText;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name="topic_id")
    private Topic topic;
}
