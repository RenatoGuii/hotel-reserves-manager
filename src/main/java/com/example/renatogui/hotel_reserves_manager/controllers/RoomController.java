package com.example.renatogui.hotel_reserves_manager.controllers;

import com.example.renatogui.hotel_reserves_manager.DTOs.RoomDTO;
import com.example.renatogui.hotel_reserves_manager.models.room.RoomModel;
import com.example.renatogui.hotel_reserves_manager.services.RoomService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("rooms")
public class RoomController {

    @Autowired
    RoomService roomService;

    @PostMapping
    public ResponseEntity<RoomModel> postRoom(@RequestBody @Valid RoomDTO data) {
        RoomModel newRoom = roomService.postRoom(data);
        return ResponseEntity.status(HttpStatus.CREATED).body(newRoom);
    }

    @GetMapping("{id}")
    public ResponseEntity<RoomModel> getRoom(@PathVariable(value = "id") String id) {
        RoomModel room = roomService.getRoom(id);
        return ResponseEntity.status(HttpStatus.OK).body(room);
    }

    @GetMapping
    public ResponseEntity<List<RoomModel>> getAllRooms() {
        List<RoomModel> listRooms = roomService.getAllRooms();
        return ResponseEntity.status(HttpStatus.OK).body(listRooms);
    }

    @PutMapping("{id}")
    public ResponseEntity<RoomModel> updateRoom (@PathVariable(value = "id") String id, @RequestBody @Valid RoomDTO data) {
        RoomModel updatedRoom = roomService.updateRoom(id, data);
        return ResponseEntity.status(HttpStatus.OK).body(updatedRoom);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> deleteRoom (@PathVariable(value = "id") String id) {
        roomService.deleteRoom(id);
        return ResponseEntity.status(HttpStatus.OK).body("Room successfully deleted");
    }

}
