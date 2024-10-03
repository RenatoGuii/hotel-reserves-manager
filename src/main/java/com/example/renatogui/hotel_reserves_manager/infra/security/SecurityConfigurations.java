package com.example.renatogui.hotel_reserves_manager.infra.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfigurations {

    @Autowired
    SecurityFilter securityFilter;

    // SecurityFilterChain = Filtros de segurança checados após a requisição do usuário

    // Não esquecer do Bean
    @Bean
    public SecurityFilterChain securityFilterChain (HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                // Desabilitar as configurações padrões, pois vamos utilizar tokens ao invés de cookies
                .csrf(csfr -> csfr.disable())
                // Define que a aplicação não deve manter estado de sessão. A autenticação será gerenciada por tokens, e não por sessões de servidor.
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                // Regras de Autorização
                .authorizeHttpRequests(authorize -> authorize
                        // permitAll() = São permitidas para todos (sem necessidade de autenticação)
                        .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/auth/register").permitAll()
                        .requestMatchers(HttpMethod.POST, "/rooms").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.PUT, "/rooms").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.DELETE, "/rooms").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.DELETE, "/booking").hasRole("MANAGER")
                        .anyRequest().authenticated()
                )
                // Adiciona um filtro customizado (securityFilter) antes do filtro padrão de autenticação por username e password (UsernamePasswordAuthenticationFilter).
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                // Criar objeto SecurityFilterChain
                .build();
    }

    // Responsável por coordenar o processo de autenticação quando um usuário tenta fazer login na aplicação.
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
