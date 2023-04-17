package com.example.demo.services;

import com.example.demo.models.dtos.Auth.AuthAccessDto;
import com.example.demo.models.dtos.Auth.AuthFullDto;
import com.example.demo.models.dtos.User.CreateUserDto;
import com.example.demo.models.dtos.User.UserFullDto;
import com.example.demo.models.entites.AuthEntity;
import com.example.demo.models.entites.UserEntity;
import com.example.demo.models.mappers.UserMapper;
import com.example.demo.repositories.AuthRepository;
import com.example.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthRepository authRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UserMapper userMapper;

    @Transactional
    public Optional<UserFullDto> findUser(Long id) {
        UserEntity entity = userRepository.findById(id).get();
        UserFullDto dto = userMapper.entityToFullDto(entity);
        return Optional.ofNullable(dto);
    }

    @Transactional
    public UserFullDto createUser(CreateUserDto userDTO) {
        UserEntity userEntity = userMapper.dtoToEntity(userDTO);

        AuthEntity credential = new AuthEntity();
        credential.setUsername(userDTO.getUsername());

        String hashedPassword = passwordEncoder.encode(userDTO.getPassword());
        credential.setPassword(hashedPassword);

        credential.setUser(userEntity);
        userEntity.setCredential(credential);

        UserEntity savedUser = userRepository.save(userEntity);;
        return userMapper.entityToFullDto(savedUser);
    }
}
