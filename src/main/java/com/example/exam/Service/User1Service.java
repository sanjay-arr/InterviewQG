package com.example.exam.Service;

import com.example.exam.Entity.User1;
import com.example.exam.Repository.User1Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class User1Service {

    @Autowired
    private User1Repository user1Repository;
    
    public User1 createUser(User1 user){
        return user1Repository.save(user);
    }

    public User1 getUserById(Long id){
        return user1Repository.findById(id).orElseThrow(() -> new RuntimeException("Todo not found"));
    }
}