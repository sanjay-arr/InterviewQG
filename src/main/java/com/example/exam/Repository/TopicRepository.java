package com.example.exam.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.exam.Entity.Topic;
public interface TopicRepository extends JpaRepository<Topic, Long> {

    Optional<Topic> findByName(String name);   
}