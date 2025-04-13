package com.BarberClub.controllers;

import com.BarberClub.DTOs.LoginDTO;
import com.BarberClub.DTOs.TokenResponseDTO;
import com.BarberClub.DTOs.RegisterDTO;
import com.BarberClub.infra.ExceptionHandler.Exceptions.EmailAlreadyExistsException;
import com.BarberClub.infra.TokenService;
import com.BarberClub.models.User;
import com.BarberClub.repositories.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody @Valid RegisterDTO dto) {

        try {
            if (userRepository.findByEmail(dto.email()) != null) {
                throw new EmailAlreadyExistsException("Usuário já cadastrado com esse email");
            }

            String encryptedPassword = new BCryptPasswordEncoder().encode(dto.password());

            User newUser = new User(dto.name(), dto.email(), encryptedPassword, dto.role());

            userRepository.save(newUser);

            var usernamePassword = new UsernamePasswordAuthenticationToken(dto.email(), dto.password());

            var auth = authenticationManager.authenticate(usernamePassword);

            var token = tokenService.generateToken((User) auth.getPrincipal());

            return ResponseEntity.ok(new TokenResponseDTO(token));
        } catch (EmailAlreadyExistsException e) {
            throw e;
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erro ao registrar usuário: " + e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginDTO dto) {

        try {

            var usernamePassword = new UsernamePasswordAuthenticationToken(dto.email(), dto.password());

            var auth = authenticationManager.authenticate(usernamePassword);

            var token = tokenService.generateToken((User) auth.getPrincipal());

            return ResponseEntity.ok(new TokenResponseDTO(token));
        } catch (BadCredentialsException e) {
            throw e;
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erro ao fazer login: " + e.getMessage());
        }
    }
}

