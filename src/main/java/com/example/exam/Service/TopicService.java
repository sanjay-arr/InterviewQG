package com.example.exam.Service;

import com.example.exam.Entity.Topic;
import com.example.exam.Repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TopicService {

    @Autowired
    private TopicRepository topicRepository;

    public Topic saveTopic(Topic topic) {
        return topicRepository.save(topic);
    }

    public Optional<Topic> getTopicById(Long topicId) {
        return topicRepository.findById(topicId);
    }

    public List<Topic> getAllTopics() {
        return topicRepository.findAll();
    }

    public Topic updateTopic(Topic topic) {
        return topicRepository.save(topic);
    }

    public void deleteTopic(Long topicId) {
        topicRepository.deleteById(topicId);
    }

    public Optional<Topic> getTopicByName(String name) {
        return topicRepository.findByName(name);
    }
}