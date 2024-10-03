package com.example.renatogui.hotel_reserves_manager.repositories;

import com.example.renatogui.hotel_reserves_manager.models.user.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<UserModel, String> {
    UserDetails findByUsername(String username);
    UserDetails findByEmail(String email);
}
