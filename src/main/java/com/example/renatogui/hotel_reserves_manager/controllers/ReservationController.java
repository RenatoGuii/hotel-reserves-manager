package com.example.renatogui.hotel_reserves_manager.controllers;

import com.example.renatogui.hotel_reserves_manager.DTOs.ReservationDTO;
import com.example.renatogui.hotel_reserves_manager.models.reservation.ReservationModel;
import com.example.renatogui.hotel_reserves_manager.services.ReservationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("booking")
public class ReservationController {

    @Autowired
    ReservationService reservationService;

    @PostMapping
    public ResponseEntity<ReservationModel> postReservation(@RequestBody @Valid ReservationDTO data) {
        ReservationModel newReservation = reservationService.postReservation(data);
        return ResponseEntity.status(HttpStatus.CREATED).body(newReservation);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> deleteReservation(@PathVariable(value = "id") String id) {
        reservationService.deleteReservation(id);
        return ResponseEntity.status(HttpStatus.OK).body("Booking successfully deleted");
    }

    @DeleteMapping("/cancel/{id}")
    public ResponseEntity<Object> cancelReservation(@PathVariable(value = "id") String id) {
        reservationService.cancelReservation(id);
        return ResponseEntity.status(HttpStatus.OK).body("Booking successfully deleted");
    }

}
