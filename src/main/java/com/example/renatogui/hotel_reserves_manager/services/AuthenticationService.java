package com.example.renatogui.hotel_reserves_manager.services;

import com.example.renatogui.hotel_reserves_manager.DTOs.userDtos.AuthenticationDTO;
import com.example.renatogui.hotel_reserves_manager.DTOs.userDtos.RegisterDTO;
import com.example.renatogui.hotel_reserves_manager.infra.security.TokenService;
import com.example.renatogui.hotel_reserves_manager.models.user.UserModel;
import com.example.renatogui.hotel_reserves_manager.repositories.UserRepository;
import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    TokenService tokenService;

    public Object userLogin(AuthenticationDTO data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.username(), data.password());
        var auth = authenticationManager.authenticate(usernamePassword);

        // Gerar Token JWT
        var token = tokenService.generateToken((UserModel) auth.getPrincipal());
        return token;
    }

    public void userRegister(RegisterDTO data) throws AuthenticationException {
        if (userRepository.findByEmail(data.email()) != null) {
            throw new AuthenticationException("There is already a user using this email!");
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        UserModel newUser = new UserModel(data.username(), encryptedPassword, data.email(), data.role());
        userRepository.save(newUser);
    }

}
