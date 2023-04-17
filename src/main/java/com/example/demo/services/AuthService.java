package com.example.demo.services;

import com.example.demo.models.dtos.Auth.AuthFullDto;
import com.example.demo.models.dtos.Auth.AuthLoginDto;
import com.example.demo.models.entites.AuthEntity;
import com.example.demo.models.mappers.AuthMapper;
import com.example.demo.repositories.AuthRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class AuthService {

    @Autowired
    private AuthRepository authRepository;

    @Autowired
    private AuthMapper authMapper;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public Optional<AuthEntity> findCredentials(AuthLoginDto dto) {
        AuthEntity entity = new AuthEntity();
        entity.setUsername(dto.getUsername());

        log.info("$2a$10$4hZ/.Amu4g1rL7UPYvfO2e5dyZmtCqA7zeiLKP6rXmjLvMTM7pwmi");
        String hashedPassword = passwordEncoder.encode(dto.getPassword());
        entity.setPassword("$2a$10$4hZ/.Amu4g1rL7UPYvfO2e5dyZmtCqA7zeiLKP6rXmjLvMTM7pwmi");

        Example<AuthEntity> example = Example.of(entity);
        return authRepository.findOne(example);
    }

    public Optional<AuthFullDto> findAuth(Long id) {
        AuthEntity entity = authRepository.findById(id).get();
        AuthFullDto dto = authMapper.entityToFullDto(entity);
        return Optional.ofNullable(dto);
    }

}
