package com.example.renatogui.hotel_reserves_manager.DTOs.userDtos;

import jakarta.validation.constraints.NotBlank;

public record LoginResponseDTO(@NotBlank String token) {
}
