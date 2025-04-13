package com.BarberClub.controllers;

import com.BarberClub.DTOs.UpdateUserDTO;
import com.BarberClub.infra.ExceptionHandler.Exceptions.UserNotFoundException;
import com.BarberClub.models.enums.UserRole;
import com.BarberClub.repositories.UserRepository;
import com.BarberClub.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public ResponseEntity<?> getAllUsers() {

        try {
            var getAllUsers = userService.listUsers();

            return ResponseEntity.ok(getAllUsers);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erro ao buscar usuarios: " + e.getMessage());
        }
    }

    @PutMapping("/{userId}")
    public ResponseEntity<?> updateUser(@PathVariable("userId") Long userId, @RequestBody UpdateUserDTO updateUserDTO, UserRole role) {

        try {
            userService.updateUser(userId, updateUserDTO, role);

            return ResponseEntity.ok().build();
        } catch (UserNotFoundException e) {
            throw e;
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erro ao atualizar usuario: " + e.getMessage());
        }
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteClient(@PathVariable("userId") Long userId) {

        try {
            userService.deleteUser(userId);

            return ResponseEntity.ok().build();
        } catch (UserNotFoundException e) {
            throw e;
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erro ao excluir usuario: " + e.getMessage());
        }
    }
}

