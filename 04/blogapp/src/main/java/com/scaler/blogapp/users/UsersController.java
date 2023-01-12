package com.scaler.blogapp.users;

import com.scaler.blogapp.users.dtos.CreateUserDto;
import com.scaler.blogapp.users.dtos.LoginUserDto;
import com.scaler.blogapp.users.dtos.UserResponseDto;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/users")
public class UsersController {
    private final UsersService userService;

    public UsersController(UsersService userService) {
        this.userService = userService;
    }

    @PostMapping("")
    public ResponseEntity<UserResponseDto> createUser(@RequestBody CreateUserDto userDto) {
        UserResponseDto responseDto = userService.createUser(userDto);

        return ResponseEntity
                .created(URI.create("/users/" + responseDto.getId()))
                .body(responseDto);
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponseDto> verifyUser(@RequestBody LoginUserDto requestDto) {
        var responseDto = userService.verifyUser(requestDto);

        return ResponseEntity
                .ok(responseDto);
    }
}
