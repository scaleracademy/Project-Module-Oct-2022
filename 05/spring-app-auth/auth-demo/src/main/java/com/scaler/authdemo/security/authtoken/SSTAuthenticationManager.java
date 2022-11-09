package com.scaler.authdemo.security.authtoken;

import com.scaler.authdemo.users.UserEntity;
import com.scaler.authdemo.users.UsersService;
import com.scaler.authdemo.users.dtos.UserResponseDto;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

public class SSTAuthenticationManager implements AuthenticationManager {

    private AuthTokenService authTokenService;

    public SSTAuthenticationManager(AuthTokenService authTokenService) {
        this.authTokenService = authTokenService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if (authentication instanceof SSTAuthentication) {
            SSTAuthentication authentication1 = (SSTAuthentication) authentication;
            var tokenString = authentication1.getCredentials();
            var user = authTokenService.getUserFromToken(tokenString);
            authentication1.setUser(new UserResponseDto(user.getId(), user.getUsername(), user.getEmail(), tokenString));

            return authentication1;
        }
        return null;
    }
}
