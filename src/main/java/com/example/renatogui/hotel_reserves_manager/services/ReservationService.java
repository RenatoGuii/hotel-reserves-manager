package com.example.renatogui.hotel_reserves_manager.services;

import com.example.renatogui.hotel_reserves_manager.DTOs.ReservationDTO;
import com.example.renatogui.hotel_reserves_manager.exceptions.EntityNotFoundException;
import com.example.renatogui.hotel_reserves_manager.exceptions.ReservationException;
import com.example.renatogui.hotel_reserves_manager.models.reservation.ReservationModel;
import com.example.renatogui.hotel_reserves_manager.repositories.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class ReservationService {

    @Autowired
    ReservationRepository reservationRepository;

    public ReservationModel postReservation(ReservationDTO data) {
        ReservationModel newReservation = new ReservationModel();

        if (data.check_out().isBefore(data.check_in())) {
            throw new ReservationException("The check-in date cannot be less than the check-out date");
        }

        if (!reservationRepository.findReservationsForRoom(data.id_room(), data.check_in(), data.check_out()).isEmpty()) {
            throw new ReservationException("Date unavailable for booking");
        }

        newReservation.setReservationStatus(data.reservation_status());
        newReservation.setCheck_in(data.check_in());
        newReservation.setCheck_out(data.check_out());
        newReservation.setId_user(data.id_user());
        newReservation.setId_room(data.id_room());

        reservationRepository.save(newReservation);

        return newReservation;

    }

    public void deleteReservation(String id) {
        Optional<ReservationModel> reservation = reservationRepository.findById(id);

        if (reservation.isEmpty()) {
            throw new EntityNotFoundException("There is no reservation with the provided identifier");
        }

        reservationRepository.delete(reservation.get());
    }

    public void cancelReservation(String id) {
        Optional<ReservationModel> reservation = reservationRepository.findById(id);

        if (reservation.isEmpty()) {
            throw new EntityNotFoundException("There is no reservation with the provided identifier");
        }

        if (LocalDate.now().isAfter(reservation.get().getCheck_in())) {
            throw new ReservationException("It is not possible to cancel reservations after the check-in date");
        }

        reservationRepository.delete(reservation.get());
    }

}
