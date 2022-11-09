package com.scaler.authdemo.security.authtoken;

import com.scaler.authdemo.users.UserEntity;
import com.scaler.authdemo.users.UsersRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AuthTokenService {
    private AuthTokenRepository authTokenRepo;
    private UsersRepository usersRepo;

    public AuthTokenService(AuthTokenRepository authTokenRepo, UsersRepository usersRepo) {
        this.authTokenRepo = authTokenRepo;
        this.usersRepo = usersRepo;
    }

    public String createToken(UserEntity userEntity) {
        var token = new AuthTokenEntity();
        token.setUser(userEntity);
        authTokenRepo.save(token);
        return token.getToken().toString();
    }

    public UserEntity getUserFromToken(String tokenString) {
        var token = UUID.fromString(tokenString);
        var authToken = authTokenRepo.findById(token).get();
        return authToken.getUser();
    }
}
