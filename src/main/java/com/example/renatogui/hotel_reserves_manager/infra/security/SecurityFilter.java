package com.example.renatogui.hotel_reserves_manager.infra.security;

import com.example.renatogui.hotel_reserves_manager.repositories.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    TokenService tokenService;

    @Autowired
    UserRepository userRepository;

    // Ele é chamado para cada requisição e resposta
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // Recupera o token de requisição HTTP
        var token = this.recoverToken(request);

        // Valida o Token e guarda as credenciais do usuário autenticado de maneira global
        if (token != null) {
            // A validação do token retorna o username (retorna username pq foi configurado no método generateToken(TokenService))
            var login = tokenService.validateToken(token);
            UserDetails user = userRepository.findByUsername(login);

            var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        // Chamar/Ir para o próximo filtro
        filterChain.doFilter(request, response);
    }

    // Extrair o token JWT do cabeçalho "Authorization" da requisição
    private String recoverToken(HttpServletRequest request) {
        var authHeader = request.getHeader("Authorization");
        if (authHeader == null) return null;
        //Retirando o Bearer e deixando só o valor do token (Bearer xxxxxxxxxxxxxxxxx)
        return authHeader.replace("Bearer ", "");
    }

}
