package com.BarberClub.services;

import com.BarberClub.DTOs.UpdateUserDTO;
import com.BarberClub.infra.ExceptionHandler.Exceptions.UserNotFoundException;
import com.BarberClub.models.User;
import com.BarberClub.models.enums.UserRole;
import com.BarberClub.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    public UserRepository userRepository;

    public List<User> listUsers() {
        return userRepository.findAll();
    }

    public void updateUser(Long studentId, UpdateUserDTO dto, UserRole role) {

        var userEntity = userRepository.findById(studentId);

        if (userEntity.isPresent()) {
            var user = userEntity.get();

            if (dto.name() != null) {
                user.setName(dto.name());
            }
            if (dto.email() != null) {
                user.setEmail(dto.email());
            }
            if(dto.password() != null){
                user.setPassword(dto.password());
            }
            if(dto.role() != null){
                user.setRole(role);
            }

            userRepository.save(user);
        } throw new UserNotFoundException("");

    }

    public void deleteUser(Long userId) {

        if (!userRepository.existsById(userId))
            throw new UserNotFoundException("Usuario com Id " + userId + " nao encontrado");

        userRepository.deleteById(userId);
    }
}
