package com.example.renatogui.hotel_reserves_manager.DTOs.userDtos;

import com.example.renatogui.hotel_reserves_manager.models.user.UserRole;

public record RegisterDTO(String username, String password, String email, UserRole role) {
}
