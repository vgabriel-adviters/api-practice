package com.example.demo.models.mappers;

import com.example.demo.models.dtos.Auth.AuthFullDto;
import com.example.demo.models.dtos.User.CreateUserDto;
import com.example.demo.models.dtos.User.UserFullDto;
import com.example.demo.models.entites.AuthEntity;
import com.example.demo.models.entites.UserEntity;
import com.example.demo.repositories.AuthRepository;
import com.example.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserFullDto entityToFullDto(UserEntity entity) {
        UserFullDto dto = new UserFullDto();
        dto.setId(entity.getId());
        dto.setEmail(entity.getEmail());
        dto.setDeleted(entity.getDeleted());
        dto.setUsername(entity.getCredential().getUsername());
        dto.setPassword(entity.getCredential().getPassword());

        return dto;
    }

    public UserEntity dtoToEntity(CreateUserDto dto) {
        UserEntity entity = new UserEntity();
        entity.setEmail(dto.getEmail());
        return entity;
    }
}
