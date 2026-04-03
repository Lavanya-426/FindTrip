package com.findtripmate.modules.auth.service;

import com.findtripmate.common.exception.CustomException;
import com.findtripmate.modules.auth.dto.*;
import com.findtripmate.modules.user.entity.User;
import com.findtripmate.modules.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public void register(RegisterRequestDTO request) {

        String email = request.getEmail();

        if (!email.endsWith("@vitapstudent.ac.in")) {
            throw new CustomException("Only VIT-AP student emails allowed", HttpStatus.BAD_REQUEST);
        }

        if (userRepository.existsByEmail(email)) {
            throw new CustomException("Email already registered", HttpStatus.CONFLICT);
        }

        String hashedPassword = passwordEncoder.encode(request.getPassword());

        User user = User.builder()
                .name(request.getName())
                .email(email)
                .password(hashedPassword)
                .build();

        userRepository.save(user);
    }

    // 🔥 LOGIN LOGIC
    @Override
    public AuthResponseDTO login(LoginRequestDTO request) {

        // 1. Check user exists
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new CustomException(
                        "User not found",
                        HttpStatus.NOT_FOUND
                ));

        // 2. Verify password
        boolean isMatch = passwordEncoder.matches(
                request.getPassword(),
                user.getPassword()
        );

        if (!isMatch) {
            throw new CustomException(
                    "Invalid credentials",
                    HttpStatus.UNAUTHORIZED
            );
        }

        // 3. Return response 
        return new AuthResponseDTO("Login successful");
    }
}