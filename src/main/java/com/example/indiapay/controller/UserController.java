package com.example.indiapay.controller;

import com.example.indiapay.dto.UserDTO;
import com.example.indiapay.entities.User;
import com.example.indiapay.exceptions.BadRequestException;
import com.example.indiapay.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(value = "/users")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping(value = "/")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userRepository.findAll();
        return ResponseEntity.ok(users);
    }

    @PostMapping(value = "/")
    public ResponseEntity<User> createUser(@RequestBody @Valid UserDTO user) {
        User userEntity = new User();
        BeanUtils.copyProperties(user, userEntity);

        Optional<User> existingUser = userRepository.findUserByEmail(user.getEmail());
        if (existingUser.isPresent()) {
            throw new BadRequestException("User with same email already exist");
        }

        User newUser = userRepository.save(userEntity);
        return ResponseEntity.ok(newUser);
    }

    @DeleteMapping(value = "/{userId}")
    public ResponseEntity<Map<String, String>> deleteUser(@PathVariable long userId) {
        Map<String, String> response = new HashMap<>();
        Optional<User> user = userRepository.findById(userId);

        if (user.isEmpty()) {
            response.put("message", "User " + userId + " does not exist!");
            return ResponseEntity.ok(response);
        }
        userRepository.deleteById(userId);
        response.put("message", "User " + userId + " successfully deleted");
        return ResponseEntity.ok(response);
    }
}

