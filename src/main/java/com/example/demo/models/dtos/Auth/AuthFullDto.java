package com.example.demo.models.dtos.Auth;

import com.example.demo.models.entites.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthFullDto {

    private Long id;

    private String username;

    private String password;

    private UserEntity user;
}
