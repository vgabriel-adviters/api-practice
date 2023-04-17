package com.example.demo.models.dtos.User;

import com.example.demo.models.entites.AuthEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserFullDto {

    private Long id;

    private String username;

    private String password;

    private String email;

    private Boolean deleted;
}
