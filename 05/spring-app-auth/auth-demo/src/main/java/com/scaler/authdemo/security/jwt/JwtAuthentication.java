package com.scaler.authdemo.security.jwt;

import com.scaler.authdemo.users.dtos.UserResponseDto;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class JwtAuthentication implements Authentication {
    private String jwtString;
    private UserResponseDto user;

    public JwtAuthentication(String jwtString) {
        this.jwtString = jwtString;
    }

    void setUser(UserResponseDto user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // TODO: not needed for now, unless we have different resources accessible to different roles
        return null;
    }

    @Override
    public String getCredentials() {
        // This is where we return the string/token that is used for authentication
        return jwtString;
    }

    @Override
    public Object getDetails() {
        // TODO: not needed for now
        return null;
    }

    @Override
    public UserResponseDto getPrincipal() {
        // This is where we return the user/client who is getting authenticated
        return user;
    }

    @Override
    public boolean isAuthenticated() {
        return (user != null);
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {

    }

    @Override
    public String getName() {
        return null;
    }
}
