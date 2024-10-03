package com.example.renatogui.hotel_reserves_manager.repositories;

import com.example.renatogui.hotel_reserves_manager.models.reservation.ReservationModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface ReservationRepository extends JpaRepository<ReservationModel, String> {

    @Query(value = "SELECT * FROM reservation r " +
            "WHERE r.id_room = :roomId " +
            "AND NOT (r.check_in >= :checkOut OR r.check_out <= :checkIn)",
            nativeQuery = true)
    List<ReservationModel> findReservationsForRoom(@Param("roomId") String roomId,
                                              @Param("checkIn") LocalDate checkIn,
                                              @Param("checkOut") LocalDate checkOut);



}
