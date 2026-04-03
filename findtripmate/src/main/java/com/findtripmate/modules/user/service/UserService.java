package com.findtripmate.modules.user.service;

import com.findtripmate.modules.user.dto.UserResponseDTO;

import java.util.List;

public interface UserService {

    UserResponseDTO getUserById(Long id);

    List<UserResponseDTO> getAllUsers();
}