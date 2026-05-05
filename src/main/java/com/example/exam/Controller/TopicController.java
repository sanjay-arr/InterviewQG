package com.example.exam.Controller;

import com.example.exam.Entity.Topic;
import com.example.exam.Service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/topics")
public class TopicController {

    @Autowired
    private TopicService topicService;

    @GetMapping
    public List<Topic> getAllTopics() {
        return topicService.getAllTopics();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Topic> getTopicById(@PathVariable Long id) {
        return topicService.getTopicById(id)
                .map(topic -> ResponseEntity.ok(topic))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Topic createTopic(@RequestBody Topic topic) {
        return topicService.saveTopic(topic);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Topic> updateTopic(@PathVariable Long id, @RequestBody Topic topic) {
        if (!topicService.getTopicById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        topic.setTopicId(id);
        return ResponseEntity.ok(topicService.updateTopic(topic));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTopic(@PathVariable Long id) {
        if (!topicService.getTopicById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        topicService.deleteTopic(id);
        return ResponseEntity.noContent().build();
    }
}