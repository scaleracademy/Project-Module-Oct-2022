package com.scaler.authdemo.users;

import com.scaler.authdemo.security.AuthTokenService;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsersService {
    private UsersRepository usersRepo;
    private ModelMapper modelMapper;
    private PasswordEncoder passwordEncoder;
    private AuthTokenService authTokenService;

    public UsersService(UsersRepository usersRepo, ModelMapper modelMapper, PasswordEncoder passwordEncoder, AuthTokenService authTokenService) {
        this.usersRepo = usersRepo;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.authTokenService = authTokenService;
    }

    public UserResponseDto createUser(CreateUserDto request) {
        var user = modelMapper.map(request, UserEntity.class);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        var savedUser = usersRepo.save(user);
        var response = modelMapper.map(savedUser, UserResponseDto.class);
        var token = authTokenService.createToken(savedUser);
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
