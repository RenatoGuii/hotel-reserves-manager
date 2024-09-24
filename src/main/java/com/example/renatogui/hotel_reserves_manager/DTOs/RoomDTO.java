package com.example.renatogui.hotel_reserves_manager.DTOs;

import com.example.renatogui.hotel_reserves_manager.models.room.RoomStatus;
import com.example.renatogui.hotel_reserves_manager.models.room.RoomType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RoomDTO(@NotNull Integer number, @NotNull Integer capacity, @NotNull Double price_per_night, @NotNull RoomStatus hotel_status, @NotNull RoomType hotel_type) {
}
