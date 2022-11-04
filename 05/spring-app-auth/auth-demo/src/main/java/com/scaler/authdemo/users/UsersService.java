package com.scaler.authdemo.users;

import com.scaler.authdemo.security.authtoken.AuthTokenService;
import com.scaler.authdemo.security.jwt.JwtService;
import com.scaler.authdemo.users.dtos.CreateUserDto;
import com.scaler.authdemo.users.dtos.LoginUserDto;
import com.scaler.authdemo.users.dtos.UserResponseDto;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsersService {
    private UsersRepository usersRepo;
    private ModelMapper modelMapper;
    private PasswordEncoder passwordEncoder;
    private AuthTokenService authTokenService;
    private JwtService jwtService;

    public UsersService(UsersRepository usersRepo, ModelMapper modelMapper, PasswordEncoder passwordEncoder, AuthTokenService authTokenService, JwtService jwtService) {
        this.usersRepo = usersRepo;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.authTokenService = authTokenService;
        this.jwtService = jwtService;
    }

    public UserResponseDto createUser(CreateUserDto request) {
        var user = modelMapper.map(request, UserEntity.class);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        var savedUser = usersRepo.save(user);
        var response = modelMapper.map(savedUser, UserResponseDto.class);
        // OPTION 1: Server side token
        // var token = authTokenService.createToken(savedUser);
        // response.setToken(token);
        // OPTION 2: JWT
        var token = jwtService.createJwt(savedUser.getUsername());
        response.setToken(token);
        return response;
    }

    public UserResponseDto verifyUser(LoginUserDto request) {
        UserEntity user = usersRepo.findByUsername(request.getUsername());

        if (user == null) {
            throw new RuntimeException("User not found");
        }
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }
        var response = modelMapper.map(user, UserResponseDto.class);
        response.setToken(authTokenService.createToken(user));
        return response;
    }
}
