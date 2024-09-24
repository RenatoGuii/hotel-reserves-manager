package com.example.renatogui.hotel_reserves_manager.models.reservation;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "reservation")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReservationModel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String id_room;
    private String id_user;
    private LocalDate check_in;
    private LocalDate check_out;

    @Enumerated(EnumType.STRING)
    private ReservationStatus reservationStatus;

}
