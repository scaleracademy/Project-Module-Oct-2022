package com.scaler.blogapp.security.jwt;

import com.scaler.blogapp.users.UsersService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

public class JwtAuthenticationManager implements AuthenticationManager {
    private JwtService jwtService;
    private UsersService usersService;

    public JwtAuthenticationManager(JwtService jwtService, UsersService usersService) {
        this.jwtService = jwtService;
        this.usersService = usersService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if (authentication instanceof JwtAuthentication) {
            JwtAuthentication jwtAuthentication = (JwtAuthentication) authentication;

            var jwtString = jwtAuthentication.getCredentials();
            var username = jwtService.getUsernameFromJwt(jwtString);
            // TODO: crypto failure on jwt verification
            // TODO: check if jwt is expired

            var user = usersService.findUserByUsername(username);
            jwtAuthentication.setUser(user);
            return jwtAuthentication;
        }
        return null;
    }
}
