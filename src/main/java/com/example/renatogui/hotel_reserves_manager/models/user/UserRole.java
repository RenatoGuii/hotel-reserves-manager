package com.example.renatogui.hotel_reserves_manager.models.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserRole {
    MANAGER("MANAGER"),
    CUSTOMER("CUSTOMER");

    private String role;
}
