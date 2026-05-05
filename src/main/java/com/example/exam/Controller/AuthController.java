package com.example.exam.Controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.exam.Entity.User1;
import com.example.exam.Repository.User1Repository;
import com.example.exam.Service.User1Service;
import com.example.exam.Utils.JwtUtil;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final PasswordEncoder passwordEncoder;
    private final User1Service userService;
    private final User1Repository userRepository;
    private final JwtUtil jwtutil;
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody Map<String,String> body){
        String email=body.get("email");
        String password= passwordEncoder.encode(body.get("password"));

        if(userRepository.findByEmail(email).isPresent()){
            return new ResponseEntity<>("Email already exists",HttpStatus.CONFLICT);
        }
        userService.createUser(User1.builder().email(email).password(password).build());
        return new ResponseEntity<>("Successfully Registered",HttpStatus.CREATED);
    }
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody Map<String,String> body){
        String email=body.get("email");
        String password=body.get("password");

        var userOptional =userRepository.findByEmail(email);
        if(userOptional.isEmpty()){
            return new ResponseEntity<>("User not registered", HttpStatus.UNAUTHORIZED);
        }
        User1 user=userOptional.get();
        if(!passwordEncoder.matches(password,user.getPassword())){
            return new ResponseEntity<>("Invalid user", HttpStatus.UNAUTHORIZED);
        }
        String token=jwtutil.generateToken(email);
        return ResponseEntity.ok(Map.of("token",token));
    }
}
