package com.example.renatogui.hotel_reserves_manager.services;

import com.example.renatogui.hotel_reserves_manager.DTOs.RoomDTO;
import com.example.renatogui.hotel_reserves_manager.controllers.RoomController;
import com.example.renatogui.hotel_reserves_manager.exceptions.EntityNotFoundException;
import com.example.renatogui.hotel_reserves_manager.models.room.RoomModel;
import com.example.renatogui.hotel_reserves_manager.repositories.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class RoomService {

    @Autowired
    RoomRepository roomRepository;

    public RoomModel postRoom(RoomDTO data) {
        RoomModel newRoom = new RoomModel();

        newRoom.setCapacity(data.capacity());
        newRoom.setNumber(data.number());
        newRoom.setHotel_status(data.hotel_status());
        newRoom.setHotel_type(data.hotel_type());
        newRoom.setPrice_per_night(data.price_per_night());

        roomRepository.save(newRoom);

        return newRoom;
    }

    public RoomModel getRoom(String id) {
        Optional<RoomModel> room = roomRepository.findById(id);
        if (room.isEmpty()) {
            throw new EntityNotFoundException("There is no room with the provided identifier");
        }
        return room.get();
    }

    public List<RoomModel> getAllRooms() {
        List<RoomModel> listRooms = roomRepository.findAll();

        if (listRooms.isEmpty()) {
            throw new EntityNotFoundException("There are no registered rooms");
        }

        for (RoomModel room : listRooms) {
            room.add(linkTo(methodOn(RoomController.class).getRoom(room.getId())).withSelfRel());
        }

        return listRooms;
    }

    public RoomModel updateRoom(String id, RoomDTO data) {
        Optional<RoomModel> room = roomRepository.findById(id);

        if (room.isEmpty()) {
            throw new EntityNotFoundException("There is no room with the provided identifier");
        }

        room.get().setPrice_per_night(data.price_per_night());
        room.get().setCapacity(data.capacity());
        room.get().setNumber(data.number());
        room.get().setHotel_status(data.hotel_status());
        room.get().setHotel_type(data.hotel_type());

        roomRepository.save(room.get());

        return room.get();
    }

    public void deleteRoom(String id) {
        Optional<RoomModel> room = roomRepository.findById(id);

        if (room.isEmpty()) {
            throw new EntityNotFoundException("There is no room with the provided identifier");
        }

        roomRepository.deleteById(room.get().getId());
    }

}
