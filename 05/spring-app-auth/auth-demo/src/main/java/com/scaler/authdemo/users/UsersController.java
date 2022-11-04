package com.scaler.authdemo.users;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/users")
public class UsersController {
    private UsersService usersService;

    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @PostMapping("")
    public ResponseEntity<UserResponseDto> createUser(
            @RequestBody CreateUserDto request
    ) {
        var createdUser = usersService.createUser(request);
        return ResponseEntity.created(URI.create("/users/" + createdUser.getId())).body(createdUser);
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponseDto> verifyUser(
            @RequestBody LoginUserDto request
    ) {
        var verifiedUser = usersService.verifyUser(request);
        return ResponseEntity.ok(verifiedUser);
    }
}
