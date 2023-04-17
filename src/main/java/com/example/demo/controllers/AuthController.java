package com.example.demo.controllers;

import com.example.demo.models.dtos.Auth.AuthAccessDto;
import com.example.demo.models.dtos.Auth.AuthFullDto;
import com.example.demo.models.dtos.Auth.AuthLoginDto;
import com.example.demo.models.entites.AuthEntity;
import com.example.demo.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @GetMapping("{id}")
    private ResponseEntity<AuthFullDto> getAuth(@PathVariable Long id) {
        Optional<AuthFullDto> response = authService.findAuth(id);
        if (response.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(response.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(response.get());
        }
    }

    @PostMapping("access")
    private ResponseEntity<AuthAccessDto> getAccess(@RequestBody AuthLoginDto log) {
        Optional<AuthEntity> entity = authService.findCredentials(log);
        AuthAccessDto access = new AuthAccessDto();
        if (entity.isPresent()) {
            access.setMessage("Acceso permitido");
            access.setToken("T0k3N");
            return ResponseEntity.status(HttpStatus.ACCEPTED)
                    .body(access);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(access);
        }

    }
}
