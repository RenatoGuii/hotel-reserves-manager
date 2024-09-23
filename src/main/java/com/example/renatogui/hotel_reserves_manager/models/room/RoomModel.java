package com.example.renatogui.hotel_reserves_manager.models.room;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "room")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoomModel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private Integer number;
    private Integer capacity;
    private Double price_per_night;
    private RoomStatus hotel_status;
    private RoomType hotel_type;

}
