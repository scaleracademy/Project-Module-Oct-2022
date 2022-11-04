package com.scaler.authdemo.users.dtos;

import lombok.Data;

@Data
public class CreateUserDto {
    private String username;

    private String email;

    private String password;
}
