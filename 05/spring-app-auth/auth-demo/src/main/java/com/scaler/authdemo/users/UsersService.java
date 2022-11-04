package com.scaler.authdemo.users;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class UsersService {
    private UsersRepository usersRepo;
    private ModelMapper modelMapper;

    public UsersService(UsersRepository usersRepo, ModelMapper modelMapper) {
        this.usersRepo = usersRepo;
        this.modelMapper = modelMapper;
    }

    public UserResponseDto createUser(CreateUserDto request) {
        UserEntity user = modelMapper.map(request, UserEntity.class);
        return modelMapper.map(usersRepo.save(user), UserResponseDto.class);
    }

    public UserResponseDto verifyUser(LoginUserDto request) {
        UserEntity user = usersRepo.findByUsername(request.getUsername());

        if (user == null) {
            throw new RuntimeException("User not found");
        }
        if (!user.getPassword().equals(request.getPassword())) {
            throw new RuntimeException("Invalid password");
        }
        return modelMapper.map(user, UserResponseDto.class);
    }
}
