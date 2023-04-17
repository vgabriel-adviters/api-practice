package com.example.demo.controllers;

import com.example.demo.models.dtos.Auth.AuthFullDto;
import com.example.demo.models.dtos.User.CreateUserDto;
import com.example.demo.models.dtos.User.UserFullDto;
import com.example.demo.models.entites.UserEntity;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository repository;

    @GetMapping("/{id}")
    public ResponseEntity<UserFullDto> getUser(@PathVariable Long id) {
        Optional<UserFullDto> response = userService.findUser(id);
        if (response.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(response.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(response.get());
        }

    }

    @PostMapping
    public ResponseEntity<UserFullDto> createUser(@RequestBody CreateUserDto userDto) {
        UserFullDto savedUser = userService.createUser(userDto);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }
}
