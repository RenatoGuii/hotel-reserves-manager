package com.example.renatogui.hotel_reserves_manager.DTOs;

import com.example.renatogui.hotel_reserves_manager.models.reservation.ReservationStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record ReservationDTO(@NotBlank String id_room, @NotBlank String id_user, @NotNull LocalDate check_in, @NotNull LocalDate check_out, @NotNull ReservationStatus reservation_status) {
}
