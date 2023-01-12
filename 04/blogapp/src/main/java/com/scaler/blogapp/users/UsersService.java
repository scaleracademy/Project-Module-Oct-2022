package com.scaler.blogapp.users;

import com.scaler.blogapp.security.authtoken.AuthTokenService;
import com.scaler.blogapp.security.jwt.JwtService;
import com.scaler.blogapp.security.sst.SstAuthentication;
import com.scaler.blogapp.security.sst.SstService;
import com.scaler.blogapp.users.dtos.CreateUserDto;
import com.scaler.blogapp.users.dtos.LoginUserDto;
import com.scaler.blogapp.users.dtos.UserResponseDto;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsersService {
    private final UsersRepository usersRepo;
    private final SstService sstService;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;
    private final JwtService jwtService;

    public UsersService(
            UsersRepository usersRepo,
            SstService sstService,
            PasswordEncoder passwordEncoder,
            ModelMapper modelMapper,
            JwtService jwtService
    ) {
        this.usersRepo = usersRepo;
        this.sstService = sstService;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
        this.jwtService = jwtService;
    }

    public UserResponseDto createUser(CreateUserDto requestDto) {
        // todo: data validation
        UsersEntity user = modelMapper.map(requestDto, UsersEntity.class);
        user.setPassword(passwordEncoder.encode(requestDto.getPassword()));
        UsersEntity savedUser = usersRepo.save(user);
        UserResponseDto responseDto = modelMapper.map(savedUser, UserResponseDto.class);

        // OPTION 1: Server-side token
        var sstToken = sstService.createSst(savedUser);
        // OPTION 2: JWT Token
        var jwtToken = jwtService.createJwt(savedUser.getUsername());

        responseDto.setToken(jwtToken);
        return responseDto;
    }

    public UserResponseDto verifyUser(LoginUserDto requestDto) {
        UsersEntity user = usersRepo.findByUsername(requestDto.getUsername());
        if(user == null)
            throw new RuntimeException("User not found!");
        if(!passwordEncoder.matches(requestDto.getPassword(), user.getPassword()))
            throw new RuntimeException("Invalid password!");

        var response = modelMapper.map(user, UserResponseDto.class);
//        response.setToken(authTokenService.createToken(user));
        response.setToken(jwtService.createJwt(user.getUsername()));
        return response;
    }

    public UserResponseDto findUserByUsername(String username) {
        UsersEntity user =  usersRepo.findByUsername(username);
        return modelMapper.map(user, UserResponseDto.class);
    }

//    public UsersEntity verifyUserByToken(String username, String token) {}
}
