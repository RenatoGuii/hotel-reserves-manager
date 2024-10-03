package com.example.renatogui.hotel_reserves_manager.controllers;

import com.example.renatogui.hotel_reserves_manager.DTOs.userDtos.AuthenticationDTO;
import com.example.renatogui.hotel_reserves_manager.DTOs.userDtos.LoginResponseDTO;
import com.example.renatogui.hotel_reserves_manager.DTOs.userDtos.RegisterDTO;
import com.example.renatogui.hotel_reserves_manager.services.AuthenticationService;
import jakarta.validation.Valid;
import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthenticationController {

    @Autowired
    AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<Object> registerUser(@RequestBody @Valid RegisterDTO data) throws AuthenticationException {
        authenticationService.userRegister(data);
        return ResponseEntity.status(HttpStatus.OK).body("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<Object> loginUser(@RequestBody @Valid AuthenticationDTO data) {
        Object token = authenticationService.userLogin(data);
        return ResponseEntity.status(HttpStatus.OK).body(new LoginResponseDTO(token.toString()));
    }

}
