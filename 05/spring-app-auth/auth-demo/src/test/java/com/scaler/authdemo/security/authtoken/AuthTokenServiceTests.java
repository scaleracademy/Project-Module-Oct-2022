package com.scaler.authdemo.security.authtoken;

import com.scaler.authdemo.security.jwt.JwtService;
import com.scaler.authdemo.users.UsersRepository;
import com.scaler.authdemo.users.UsersService;
import com.scaler.authdemo.users.dtos.CreateUserDto;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@DataJpaTest
public class AuthTokenServiceTests {
    @Autowired UsersRepository userRepo;
    @Autowired AuthTokenRepository authTokenRepository;

    private UsersService usersService;
    private AuthTokenService authTokenService;
    private JwtService jwtService;

    @BeforeEach
    void setup () {
        authTokenService = new AuthTokenService(authTokenRepository, userRepo);
        usersService = new UsersService(userRepo, new ModelMapper(), new BCryptPasswordEncoder(), authTokenService, null);
    }


    @Test
    void createToken_works_with_userentity() {
        usersService.createUser(new CreateUserDto(
                "arnav",
                "arnav@scaler.com",
                "abcd1234"
        ));
        var userEntity = userRepo.findByUsername("arnav");
        authTokenService.createToken(userEntity);
    }

}
