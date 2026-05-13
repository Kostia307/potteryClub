package com.example.potteryclub.service;

import com.example.potteryclub.AuthLoginResponse;
import com.example.potteryclub.model.UserEntity;
import com.example.potteryclub.repository.UserRepository;
import com.example.potteryclub.security.JwtUtil;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void register(String email, String password) {

        if (userRepository.findByEmail(email).isPresent()) {
            throw new RuntimeException("User already exists");
        }

        UserEntity user = new UserEntity();
        user.setEmail(email);
        user.setPassword(password);
        user.setAdmin(false);

        userRepository.save(user);
    }

    public AuthLoginResponse login(String email, String password) {
        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!user.getPassword().equals(password)) {
            throw new RuntimeException("Invalid credentials");
        }

        String token = JwtUtil.generateToken(user.getEmail());
        return new AuthLoginResponse(token, user.getId(), user.isAdmin());
    }
}
