package com.example.renatogui.hotel_reserves_manager.services;

import com.example.renatogui.hotel_reserves_manager.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    // O método loadUserByUsername é chamado automaticamente pelo Spring Security quando você tenta autenticar um usuário
    // usando o AuthenticationManager ou o UsernamePasswordAuthenticationToken.
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }

}
