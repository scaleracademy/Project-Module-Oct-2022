package com.scaler.blogapp.security.sst;

import com.scaler.blogapp.users.dtos.UserResponseDto;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class SstAuthentication implements Authentication {
    private final String ssToken;
    private UserResponseDto user;

    public SstAuthentication(String ssToken) {
        this.ssToken = ssToken;
    }

    public void setUser(UserResponseDto user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getCredentials() {
        // This is where we return the string/jwtString that is used for authentication
        return ssToken;
    }

    @Override
    public Object getDetails() {
        return null;
    }

    @Override
    public UserResponseDto getPrincipal() {
        // This is where we return the user/client who is getting authenticated
        return user;
    }

    @Override
    public boolean isAuthenticated() {
        return user != null;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {

    }

    @Override
    public String getName() {
        return null;
    }
}
