package com.example.demo.models.mappers;

import com.example.demo.models.dtos.Auth.AuthFullDto;
import com.example.demo.models.entites.AuthEntity;
import org.springframework.stereotype.Component;

@Component
public class AuthMapper {

    public AuthFullDto entityToFullDto(AuthEntity entity) {
        AuthFullDto dto = new AuthFullDto();
        dto.setId(entity.getId());
        dto.setUsername(entity.getUsername());
        dto.setPassword(entity.getPassword());
        dto.setUser(entity.getUser());
        return dto;
    }
}
