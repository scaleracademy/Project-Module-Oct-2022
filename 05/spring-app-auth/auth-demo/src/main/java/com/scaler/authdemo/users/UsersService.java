package com.scaler.authdemo.users;

import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsersService {
    private UsersRepository usersRepo;
    private ModelMapper modelMapper;
    private PasswordEncoder passwordEncoder;

    public UsersService(UsersRepository usersRepo, ModelMapper modelMapper, PasswordEncoder passwordEncoder) {
        this.usersRepo = usersRepo;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public UserResponseDto createUser(CreateUserDto request) {
        UserEntity user = modelMapper.map(request, UserEntity.class);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        return modelMapper.map(usersRepo.save(user), UserResponseDto.class);
    }

    public UserResponseDto verifyUser(LoginUserDto request) {
        UserEntity user = usersRepo.findByUsername(request.getUsername());

        if (user == null) {
            throw new RuntimeException("User not found");
        }
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }
        return modelMapper.map(user, UserResponseDto.class);
    }
}
