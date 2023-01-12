package com.scaler.blogapp.security.authtoken;

import com.scaler.blogapp.users.UsersEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AuthTokenService {
    private AuthTokenRepository authTokenRepo;

    public AuthTokenService(AuthTokenRepository authTokenRepo) {
        this.authTokenRepo = authTokenRepo;
    }

    public String createToken(UsersEntity user) {
        var token = new AuthTokenEntity();
        token.setUser(user);
        authTokenRepo.save(token);
        return token.getToken().toString();
    }

    public UsersEntity getUserFromToken(String token) {
        var authToken = authTokenRepo.findById(UUID.fromString(token))
                .orElseThrow(() -> new RuntimeException("Invalid token!!"));
        return authToken.getUser();
    }
}
