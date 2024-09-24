package com.example.renatogui.hotel_reserves_manager.repositories;

import com.example.renatogui.hotel_reserves_manager.models.room.RoomModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<RoomModel, String> {
}
