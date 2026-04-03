package com.findtripmate.modules.auth.service;

import com.findtripmate.modules.auth.dto.AuthResponseDTO;
import com.findtripmate.modules.auth.dto.LoginRequestDTO;
import com.findtripmate.modules.auth.dto.RegisterRequestDTO;

public interface AuthService {
    AuthResponseDTO login(LoginRequestDTO request);
    void register(RegisterRequestDTO request);
}